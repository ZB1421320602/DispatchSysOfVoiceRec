package com.telecom.ccs.utils.file;

public class SupportFormat {

    private String supportFormatStr = "pcm wav vox";
    private String[] supportFormatList = null;

    public SupportFormat(String supportFormat)
    {
        if(null!=supportFormat)
        {
            supportFormatStr = supportFormat.trim();
            supportFormatList = supportFormatStr.split(" ");
        }
    }

    public boolean isSupport(String format)
    {
        boolean isSupport = false;
        if(null == supportFormatList) return true;
        for(String fmt : supportFormatList)
        {
            if(fmt.equalsIgnoreCase(format))
            {
                isSupport = true;
                break;
            }
//			System.out.println(fmt);
        }
        return isSupport;
    }


    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }
}