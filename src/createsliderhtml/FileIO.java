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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ALuedecke
 */
public class FileIO {

    // Public methods

    /**
     *
     * @param folder_name
     * @return List of file names
     */
    public List<String> getFolderContent(String folder_name) {
        File folder = new File(folder_name);
        FilenameFilter filter = (File path, String name) -> (
            name.toLowerCase().endsWith(".bmp") ||
            name.toLowerCase().endsWith(".gif") ||
            name.toLowerCase().endsWith(".jpg") ||
            name.toLowerCase().endsWith(".png")
        );
        List<String> names = new ArrayList<>();

        for (File file : folder.listFiles(filter)) {
            if (file.isFile()) {
                names.add(file.getName());
            }
        }

        return names;
    }

    /**
     *
     * @param file_names
     * @param target_name
     */
    public void writeFile(List<String> file_names, String target_name) {
        BufferedWriter writer;
        File target;
        FileOutputStream out;
        String template;

        try {
            if (target_name.equals("")) {
                target = new File(ReplaceTags.getSlider_file());
            } else {
                target = new File(target_name);
            }
            out = new FileOutputStream(target);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            template = fillTemplate(file_names);

            writer.write(template);
            writer.flush();
            writer.close();
            out.flush();
            out.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileIO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeFile(List<String> file_names) {
        writeFile(file_names, "");
    }

    /**
     *
     * @param img_folder
     */
    public void writeFile(String img_folder) {
        writeFile(getFolderContent(img_folder), "");
    }

    /**
     *
     * @param img_folder
     * @param target_name
     */
    public void writeFile(String img_folder, String target_name) {
        System.out.println("Image Folder: " + img_folder);
        writeFile(getFolderContent(img_folder), target_name);
    }

    // Private methods

    private String fillTemplate(List<String> file_names) throws IOException {
        int cnt = 0;
        StringBuilder a_tag = new StringBuilder();
        StringBuilder li_tag = new StringBuilder();
        String a_line;
        String li_line;
        String template = getTemplate();

        for (String name : file_names) {
            a_line  = ReplaceTags.getBULLET_TAG();
            a_line = a_line.replaceAll(ReplaceTags.getPH_IMAGE_FILE(), name);
            a_line = a_line.replaceAll(ReplaceTags.getPH_IMAGE_NAME(), name.substring(0, name.lastIndexOf(".")));
            a_line = a_line.replaceAll(ReplaceTags.getPH_ID(), "" + (cnt + 1));

            li_line = ReplaceTags.getIMAGE_TAG();
            li_line = li_line.replaceAll(ReplaceTags.getPH_IMAGE_FILE(), name);
            li_line = li_line.replaceAll(ReplaceTags.getPH_IMAGE_NAME(), name.substring(0, name.lastIndexOf(".")));
            li_line = li_line.replaceAll(ReplaceTags.getPH_ID(), "" + cnt);

            if (cnt == 0) {
                a_tag.append(a_line);
                li_tag.append(li_line);
            } else {
                a_tag.append("\n          ").append(a_line);
                li_tag.append("\n          ").append(li_line);
            }

            System.out.println(" " + name);

            cnt++;
        }

        template = template.replaceAll(ReplaceTags.getPH_BULLET_TAGS(), a_tag.toString());
        template = template.replaceAll(ReplaceTags.getPH_IMAGE_TAGS(), li_tag.toString());

        System.out.println("\nFilled template:\n" + template);

        return template;
    }

    private String getTemplate() throws FileNotFoundException, IOException {
        boolean first_line = true;
        FileInputStream in = new FileInputStream(ReplaceTags.getTemplate_file());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            if (first_line) {
                out.append(line);
                first_line = false;
            } else {
                out.append("\n").append(line);
            }

        }

        return out.toString();
    }
}
