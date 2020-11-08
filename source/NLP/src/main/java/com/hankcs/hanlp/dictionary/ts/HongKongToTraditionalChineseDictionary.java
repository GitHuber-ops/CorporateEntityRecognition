
package com.hankcs.hanlp.dictionary.ts;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;

import java.util.TreeMap;

import static com.hankcs.hanlp.utility.Predefine.logger;

/**
 * 香港繁体转繁体
 * @author hankcs
 */
public class HongKongToTraditionalChineseDictionary extends BaseChineseDictionary
{
    static AhoCorasickDoubleArrayTrie<String> trie = new AhoCorasickDoubleArrayTrie<String>();
    static
    {
        long start = System.currentTimeMillis();
        String datPath = HanLP.Config.tcDictionaryRoot + "hk2t";
        if (!load(datPath, trie))
        {
            TreeMap<String, String> hk2t = new TreeMap<String, String>();
            if (!load(hk2t, true, HanLP.Config.tcDictionaryRoot + "t2hk.txt"))
            {
                throw new IllegalArgumentException("香港繁体转繁体加载失败");
            }
            trie.build(hk2t);
            saveDat(datPath, trie, hk2t.entrySet());
        }
        logger.info("香港繁体转繁体加载成功，耗时" + (System.currentTimeMillis() - start) + "ms");
    }

    public static String convertToTraditionalChinese(String traditionalHongKongChineseString)
    {
        return segLongest(traditionalHongKongChineseString.toCharArray(), trie);
    }

    public static String convertToTraditionalChinese(char[] traditionalHongKongChineseString)
    {
        return segLongest(traditionalHongKongChineseString, trie);
    }
}
