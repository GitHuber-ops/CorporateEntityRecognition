
package com.hankcs.hanlp.corpus.util;

import com.hankcs.hanlp.corpus.io.IOUtil;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author hankcs
 */
public class DictionaryUtil
{
    /**
     * 给某个字典排序
     * @param path
     * @return
     */
    public static boolean sortDictionary(String path)
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(IOUtil.newInputStream(path), "UTF-8"));
            TreeMap<String, String> map = new TreeMap<String, String>();
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] param = line.split("\\s");
                map.put(param[0], line);
            }
            br.close();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(IOUtil.newOutputStream(path), "UTF-8"));
            for (Map.Entry<String, String> entry : map.entrySet())
            {
                bw.write(entry.getValue());
                bw.newLine();
            }
            bw.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
