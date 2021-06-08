package infomind.com.utils.file;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InfoResourceUtils {
    public static List<Resource> listResources(String dirName) throws IOException {
        List<Resource> result = new ArrayList<>();
        File dir = InfoResourceUtils.getFile(dirName);

        if (dir != null) {
            File[] files = dir.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isFile() && !pathname.isDirectory();
                }
            });

            if (files != null) {
                for (File file : files) {
                    result.add(new FileSystemResource(file));
                }
            }
        }
        return result;
    }

    public static Resource getResource(String fileName) {


        FileSystemResource fileSystemResource = new FileSystemResource(fileName);

        if (fileSystemResource.exists()) {
            return fileSystemResource;
        }

        ClassPathResource classPathResource = new ClassPathResource(fileName);

        if (classPathResource.exists()) {
            try {
                return classPathResource;
            } catch (Exception ignored) {
            }
        }

        return null;
    }

    public static File getFile(String fileName) throws IOException {

    try {




        Resource resource = getResource(fileName);

        System.out.println("resource===>"+resource);

        if (resource != null && resource.exists()) {
            return resource.getFile();
        }


    }catch (Exception e){


        System.out.println("e===>"+e);
    }

        return null;
    }
}

