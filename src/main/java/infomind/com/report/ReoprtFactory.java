package infomind.com.report;

import javax.servlet.http.HttpServletResponse;

public class ReoprtFactory  implements ReoprtConstants{



    public static void writeHeader(HttpServletResponse response,String pReportDownloadFileNm)
    {
        try
        {

            String reportDownloadFileNm =(pReportDownloadFileNm == null || pReportDownloadFileNm.equals("")) ? "report" :""+ pReportDownloadFileNm;

            System.out.println("reportDownloadFileNm==>"+reportDownloadFileNm);

            response.setContentType(SET_CONTENT_TYPE);
            response.setHeader( HEADER_KEY_DISPOSITION, "inline; filename=" +reportDownloadFileNm+FILE_TYPE );

        }
        catch ( Exception e )
        {
            //Log.error( "writeHeader Error", e );
        }
    }

    public static String reportPath(String pPath ,String pFileNm){


        String path =(pPath == null || pPath.equals("")||"".equals(pPath)) ? "" :pPath+"/";

        String returnPath =JASPERREPORTS_PATH+path+pFileNm+JASPERREPORTS_FILE_TYPE;


        System.out.println("returnPath==>"+returnPath);


        return returnPath;

    }



}
