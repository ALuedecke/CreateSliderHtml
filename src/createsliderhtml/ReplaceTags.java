/*
 * Copyright (C) 2016 ALuedecke
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package createsliderhtml;

/**
 *
 * @author ALuedecke
 */
public final class ReplaceTags {
    private static final String BULLET_TAG = "<a href=\"#\" title=\":IMAGE_NAME:\">"
                                           + "<span><img src=\"data/tooltips/:FILE_NAME:\" alt=\":IMAGE_NAME:\"/>"
                                           + ":ID:</span></a>";
    private static final String IMAGE_TAG  = "<li><img src=\"data/images/:FILE_NAME:\" alt=\":IMAGE_NAME:\" "
                                           + "title=\":IMAGE_NAME:\" id=\"wows1_:ID:\"/></li>";
    private static final String PH_BULLET_TAGS = ":A_BULLET_TAGS:";
    private static final String PH_ID = ":ID:";
    private static final String PH_IMAGE_FILE = ":FILE_NAME:";
    private static final String PH_IMAGE_NAME = ":IMAGE_NAME:";
    private static final String PH_IMAGE_TAGS = ":LI_IMAGE_TAGS:";
    private static final String USAGE = "Usage:\n"
                                      + "------\n" 
                                      + "1.> java -jar CreateSliderHtml.jar [image path]\n"
                                      + "2.> java -jar CreateSliderHtml.jar [image path] [target_path]\n";
    
    private static String slider_file   = "wowslider.html";
    private static String template_file = "res/template.html";

    public static String getBULLET_TAG() {
        return BULLET_TAG;
    }

    public static String getIMAGE_TAG() {
        return IMAGE_TAG;
    }

    public static String getPH_BULLET_TAGS() {
        return PH_BULLET_TAGS;
    }

    public static String getPH_ID() {
        return PH_ID;
    }

    public static String getPH_IMAGE_FILE() {
        return PH_IMAGE_FILE;
    }

    public static String getPH_IMAGE_NAME() {
        return PH_IMAGE_NAME;
    }

    public static String getPH_IMAGE_TAGS() {
        return PH_IMAGE_TAGS;
    }

    public static String getSlider_file() {
        return slider_file;
    }

    public static void setSlider_file(String slider_file) {
        ReplaceTags.slider_file = slider_file;
    }

    public static String getTemplate_file() {
        return template_file;
    }

    public static void setTemplate_file(String template_file) {
        ReplaceTags.template_file = template_file;
    }

    public static String getUSAGE() {
        return USAGE;
    }
}
