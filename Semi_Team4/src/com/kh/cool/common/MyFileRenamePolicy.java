// 업로드 되는 파일의 이름을 재설정

package com.kh.cool.common;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{

	@Override
	public File rename(File oldFile) {

		long currentTime = System.currentTimeMillis();
		
		int randomNumber = (int) (Math.random() * 100000 );
		
		//확장자 분리
		String name = oldFile.getName();
		String body = "";
		String ext = "";
		
		int dot = name.lastIndexOf(".");
		
		//확장자 존재
		if(dot != -1) {
			body = name.substring(0, dot);
			ext = name.substring(dot);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileName = sdf.format(new Date(currentTime)) + randomNumber + ext;
		
		File newFile = new File(oldFile.getParent(), fileName);
		
		return newFile;
	}

	
	
}
