package com.exam.domain;

import lombok.Data;

@Data
public class AttachVo {
    
    /*
      
CREATE TABLE `attach` (
  `filename` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `filetype` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `uuid` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `uploadpath` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bid` int(11) NOT NULL,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci 




COMMENT='    private String filename;\n    private String filetype;\n    private String uuid;\n    private String uploadpath;\n    \n    private int bId;';



      */
    
    private String filename;
    private String filetype;
    private String uuid;
    private String uploadpath;
    
    private int bid;
    
    
}
