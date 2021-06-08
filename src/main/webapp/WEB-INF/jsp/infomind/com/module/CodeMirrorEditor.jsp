<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="validator" uri="http://www.springmodules.org/tags/commons-validator" %>

<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/lib/codemirror.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/scroll/simplescrollbars.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/fold/foldcode.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/fold/xml-fold.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/fold/indent-fold.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/fold/foldgutter.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/edit/matchtags.js"></script>
<link rel="stylesheet" href="<c:url value='/source/lib/codemirror-5.61.0'/>/lib/codemirror.css">
<link rel="stylesheet" href="<c:url value='/source/lib/codemirror-5.61.0'/>/theme/eclipse.css">
<link rel="stylesheet" href="<c:url value='/source/lib/codemirror-5.61.0'/>/theme/vscode-dark.css">
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/mode/xml/xml.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/mode/javascript/javascript.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/mode/css/css.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/mode/htmlmixed/htmlmixed.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/addon/mode/multiplex.js"></script>
<script src="<c:url value='/source/lib/codemirror-5.61.0'/>/mode/htmlembedded/htmlembedded.js"></script>
<script>
var $ifx = $ifx ? $ifx : {};

$ifx['generateCodeEditor'] = function(_textarea, _option) {
     var editor = CodeMirror.fromTextArea(_textarea, $.extend(true, {
        mode: "application/x-jsp",
        theme: 'eclipse',
        lineNumbers: true,
        scrollbarStyle: "simple",
        lineWrapping: true,
        indentUnit: 4,
        indentWithTabs: true,
        styleActiveLine: true,
        matchBrackets: true,
        matchTags: {bothTags: true},
        extraKeys: {"Ctrl-J": "toMatchingTag"},
        foldGutter: true,
        gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter"]
    }, _option));

     /*
     * <h3 class="btitle ">
        <input type="radio" name="editorTheme" id="editorTheme1" value="eclipse" checked>
        <label for="editorTheme1"><i class="bx bx-radio-circle-marked"></i>Light</label>
        <input type="radio" name="editorTheme" id="editorTheme2" value="vscode-dark">
        <label for="editorTheme2"><i class="bx bx-radio-circle-marked"></i>Dark</label>
    </h3>
     * */
     if(_option['useThemeSelector'] === true) {
        var randKey = Math.random().toString(36).substring(2, 15) + Math.random().toString(36).substring(2, 15);
        var $textarea = $(_textarea);
        var $h3 = $('<h3 />', {'class': 'btitle'})
        $h3.append($('<input />', {'type': 'radio', 'name': randKey, 'id': randKey + '.1', 'value': 'eclipse', 'checked': 'checked'}));
        $h3.append($('<label />', {'for': randKey + '.1', 'html': '<i class="bx bx-radio-circle-marked"></i>Light'}));
        $h3.append($('<input />', {'type': 'radio', 'name': randKey, 'id': randKey + '.2', 'value': 'vscode-dark'}));
        $h3.append($('<label />', {'for': randKey + '.2', 'html': '<i class="bx bx-radio-circle-marked"></i>Dark'}));

        $textarea.before($h3);

        $($h3).on('change', 'input[name='+randKey+']', function(e) {
            editor.setOption("theme", this.value);
        })
     }

    return editor;
}
</script>