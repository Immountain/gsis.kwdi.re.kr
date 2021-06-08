<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<script type="text/javascript">


    var inoutSxnMap = {};

    var firstGrid = new ax5.ui.grid();
    //firstGrid.constructor.prototype.getRow = function(i) { return this.getList()[i]}


    $(document.body).ready(function () {


        $('#procDt').val(moment().subtract(0, 'day').format('YYYY-MM-DD'));


        //시작일.
        $('#procDt').datepicker({
            language: 'ko',
            calendarWeeks: false,
            format: "yyyy-mm-dd",
            pickTime: false,
            autoclose: true,
            todayHighlight: true,
            endDate: '-1d'
        }).on('changeDate', function (selected) {
            //  var startDate = new Date(selected.date.valueOf());
            //   $('#endDay').datepicker('setStartDate', startDate);
        }).on('clearDate', function (selected) {
            // $('#endDay').datepicker('setStartDate', null);
        });




        $('#modal-close').click(function () {
            ax5modal.close()
        });



        // 그리드세팅
        firstGrid.setConfig({
                target: $('[data-ax5grid="first-grid"]'),
                sortable: true,
                showLineNumber: true,
                showRowSelector: false,
                multipleSelect: false,
                header: {
                    align: "center",
                    columnHeight: 40
                },
                body: {
                    align: "center",
                    columnHeight: 28,
                    onDBLClick: function () {

                    }

                },
                columns: [
                    // {key: "itemId", label: "품목아이디"},
                    {
                        key: "itemGb", label: "품목명", formatter: function () {

                            var statement = "";
                            switch (this.value) {
                                case 'I' :
                                    statement = this.item.serialNo;
                                    break;
                                case 'B' :
                                    statement = this.item.itemIdNm;
                                    break;
                                default:
                                    statement = '';
                                    break;
                            }
                            return statement;


                        }
                    },
                    {key: "spec", label: "규격"},
                    {key: "tempInvertorIdNm", label: "교체인버터 명"},
                    {key: "tempInvertorId", label: "교체인버터 선택",
                        editor: {type: "select", config: {
                                columnKeys: {
                                    optionValue: "id", optionText: "text"
                                },
                                options: [],
                                onChange: function(){
                                    firstGrid.setValue(this.item.row, "tempInvertorIdNm", this.value[0].text);

                                },
                                onExpand: function (callback) {
                                    console.log(this.item.gridItem.itemId)
                                    // console.log(callback.item.gridRow)
                                    var id =this.item.gridItem.itemId;
                                    setTimeout(function () {
                                        var selectTest=[];
                                        var API_SERVER = "${adminContextPath}/cm/incongruity/getTempRow.wp";
                                        var p ={invertorId:id }

                                        $.ajax({
                                            method: "GET",
                                            url: API_SERVER,
                                            data:p,
                                            success: function (res) {
                                                $.each(res, function (key, value) {
                                                    var temp ={}
                                                    temp.id =value.tempInvertorId;
                                                    temp.text =value.serialNo;
                                                    selectTest.push(temp);

                                                });
                                                callback({
                                                    options:selectTest
                                                });
                                            }
                                        });
                                    }, 500)
                                }
                            }
                        }
                    }
                ]
            }
        );

        var itemGb =$('#itemGb').val();

        if(itemGb =="I"){

            firstGrid.updateColumn({ key: "itemGb", label: "수리인버터", formatter: function () {

                    var statement = "";
                    switch (this.value) {
                        case 'I' :
                            statement = this.item.serialNo;
                            break;
                        case 'B' :
                            statement = this.item.itemIdNm;
                            break;
                        default:
                            statement = '';
                            break;
                    }
                    return statement;

                }}, 0);

        }else{

            firstGrid.updateColumn({key: "tempInvertorIdNm", label: "교체인버터" ,width: 0}, 2);
            firstGrid.updateColumn({key: "tempInvertorId", label: "교체인버터 아이디" ,width: 0}, 3);

        }

        var repairStateCd =$('#repairStateCd').val();
        console.log(repairStateCd);
        //최종 값이 발생이 아니면





        //코드 select 박스 호출
        ajaxLoadSelect({
            url: '<c:url value='${adminContextPath}/cm/sucode/tag/codeKsList.wp'/>',
            params: [
                {name: 'sid', value: 'sb_selectMultiAdmZoneCode'},
                {name: 'p1', value: 'CHECK'}
            ],
            selectboxNm: 'checkCd'
        });


        $('#btn_save').click(function () {

            var param = {};itemGb
            var repairStateCd  =$('select[name=repairStateCd]').val();
            var checkCd =$('select[name=checkCd]').val();
            var confirmYn =$('select[name=confirmYn]').val();


            param.procDt =  $('#procDt').val();
            param.content =  $('#contentNm').val();
            param.atchFileId =  $('#atchFileId').val();
            param.repairStateCd = repairStateCd;
            param.checkCd = checkCd;
            param.confirmYn = confirmYn;
            param.finalcheck ='CLOSING';

            param.incongruityNo =$('#incongruityNo').val()

            if(!$('#procDt').val()){

                alert("완료일 선택하세요");
                return;
            }



            var check =true;
            var itemGbcheck =true;
            var itemGb =$('#itemGb').val();


            $.each(firstGrid.getList(), function (key, value) {


                console.log(value);

                if(value.itemGb =='I'){

                    if( value.tempInvertorId ==""  ){
                        itemGbcheck=false;
                    }
                }


                var itemId =value.itemId;

                param['list[' + key +'].tempInvertorId'] = value.tempInvertorId;
                param['list[' + key +'].itemSeq'] = value.itemSeq;
                param['list[' + key +'].resetYn'] = 'Y';
                param['list[' + key +'].itemGb'] = value.itemGb;;
                param['list[' + key +'].itemId'] = itemId;

            });


            if(itemGbcheck ==false){

                alert("교체 인버터을 선택하세여");
                return;
            }

            var saveQuestion = confirm("저장 하시겠습니까?");
            if (saveQuestion) {
                $.ajax({
                    method: "post",
                    url: '${adminContextPath}/cm/incongruity/updata.wp',
                    data: param,
                    success: function (res) {
                        console.log(res);
                        if (res == "OK") {

                            alert("저장완료");

                            var p={};
                            ax5modal.callback(p);
                            ax5modal.close()



                        } else {
                            alert(res);
                        }
                    }
                });


            }


        })



        Search();
    });




    //그리드 데이터 로드
    function Search() {

        var incongruityNo =$('#incongruityNo').val();



        var p = {
            incongruityNo: incongruityNo
        };

        /* 그리드 데이터 가져오기 */
        var API_SERVER = "${adminContextPath}/cm/incongruity/getItemRow.wp";
        $.ajax({
            method: "GET",
            url: API_SERVER,
            data:p,
            success: function (res) {
                firstGrid.setData(res);


            }
        });
    }



    function valueCheck(){
        var i = 0;
        var tmpA;
        var tmpB;
        for(i = 1; i < firstGrid.getList().length; i++){

            for(j = 0; j < i ; j++){
                // 값비교
                tmpA = firstGrid.getList()[i].tempInvertorId;
                tmpB = firstGrid.getList()[j].tempInvertorId;

                if(tmpA == tmpB){
                    alert("동일한 교체 인버터가 있습니다");
                    return false;
                    break
                }
            }
        }
        //  alert("동일한 값이 없습니다");
        return true;
    }


    function openModal(data) {
        console.log(data.__index);
        var invertorId ="";
        var url = "/mng//cm/suRepair/listTempInvertor.wp?invertorId="+invertorId;
        ax5modal.open({
            width: 350,
            height: 400,
            iframe: {
                method: "post",
                url: url
            }
        }, function (d) {

            firstGrid.setValue(data.__index, "tempInvertorIdNm", d.serialNo);
            firstGrid.setValue(data.__index, "tempInvertorId", d.tempInvertorId);

            // console.log(d)

            //var link = "/m";
            //window.location.href = link;


        });


    }


    //최종 값이 발생이 아니면
    if(repairStateCd !="O"){
        firstGrid.addColumn({key: "resetYn", label: "수리여부", width: 50, sortable: false, align: "center", editor: {
                type: "checkbox", config: {height: 17, trueValue: "Y", falseValue: "N"}
            }});


    }

    firstGrid.setConfig({
            target: $('[data-ax5grid="first-grid"]'),
            sortable: true,
            showRowSelector: false,
            multipleSelect: false,
            showLineNumber: true,
            lineNumberColumnWidth: 40,

            header: {
                align: "center",
                columnHeight: 40
            },
            body: {
                align: "center",
                columnHeight: 28,
                onDBLClick: function () {
                    //saveView1(this.item);
                }

            },

            columns: [
                {key: "lcatcd", label: "대분류코드"},
                {key: "lcatnm", label: "대분류코드명", width: 200, editor: {type: "text"}},
                {key: "lsortno", label: "대분류정렬", editor: {type: "number"}},
                {key: "luseyn", label: "사용여부", align: "center", editor: { type: "select", config: { columnKeys: { optionText: "ID", optionValue: "TEXT" }, options: [ { ID: "Y", TEXT: "사용" }, { ID: "N", TEXT: "미사용" } ] }, disabled: function () { return this.item.complete == "false"; } } },

            ]


        }
    );



</script>