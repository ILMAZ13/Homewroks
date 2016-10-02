package ru.itis.photogallery;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ilmaz on 02.10.16.
 */

public class HtmlHelper {
    TagNode rootNode;

    public HtmlHelper(URL htmlPage) throws IOException
    {
        HtmlCleaner cleaner = new HtmlCleaner();
        rootNode = cleaner.clean(htmlPage);
    }

    public List<String> getImageLinks()
    {
        List<String> linkList = new ArrayList<>();

        TagNode imgElements[] = rootNode.getElementsByName("img", true);
        for (int i = 1; imgElements != null && i < imgElements.length; i++)
        {
            String src ="https:" +  imgElements[i].getAttributeByName("src");
            linkList.add(src);
        }

        return linkList;
    }
}
