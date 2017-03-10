package disc.mods.core.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class FileIOHelper
{
    public static String ReadFile(File file)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            return new String(data, "UTF-8");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                fis.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static void WriteFile(File file, String Content)
    {
        boolean flag = true;
        if (!file.exists())
        {
            flag = CreateFile(file);
        }
        if (flag)
        {
            try (FileWriter writer = new FileWriter(file))
            {
                writer.write(Content);
                writer.flush();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean CreateFile(File file)
    {
        try
        {
            file.getParentFile().mkdirs();
            return file.createNewFile();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
