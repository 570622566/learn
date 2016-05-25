package test.com.iteratorfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Test2 {

	public static void main(String[] args) throws Exception {
		String path = "E:/release";
		List<File> allList = new ArrayList<File>();
		LinkedList<File> list = new LinkedList<File>();

		File dir = new File(path); // 注意上下级关系是相对于这个命名空间而言
		File[] file = dir.listFiles();// 存放的是一级目录下的文件以及文件夹

		for (int i = 0; i < file.length; i++) {
			if (file[i].isDirectory()) {
				list.add(file[i]);// 如果是文件夹就加到list中 (1级文件夹)
			} else {
				// System.out.println(file[i].getAbsolutePath());//如果是文件就输出绝对路径
				allList.add(file[i]);
			}
		}
		File tmp;
		while (!list.isEmpty()) {// 遍历list中的文件夹
			tmp = list.removeFirst();// 移除并返回此列表的第一个元素
			if (tmp.isDirectory()) {
				file = tmp.listFiles();// 存放的是二级目录下的文件以及文件夹
				if (file == null)
					continue;// 如果文件夹为空就跳出，进入下一个文件夹的遍历
				for (int i = 0; i < file.length; i++) {// 遍历二级目录下的文件夹
					if (file[i].isDirectory()) {
						list.add(file[i]);// 如果是文件夹就加入到list中，会在下次循环中继续调用文件夹下的文件或者文件夹
					} else {
						// System.out.println(file[i].getAbsolutePath());
						allList.add(file[i]);
					}
				}
			} else {
				// System.out.println(tmp.getAbsolutePath());
				// allList.add(tmp);
				allList.add(tmp);
			}
		}

		JSONObject responseDetailsJson = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		List<OutFileTreeNode> outFileTreeNodeList = new ArrayList<OutFileTreeNode>();
		for (File f : allList) {
			OutFileTreeNode node= new OutFileTreeNode ();
			
			int id = f.getPath().split("\\\\").length - dir.getPath().split("\\\\").length;
			node.setId(id);
			node.setFileName(f.getPath());
			node.setParent(f.getParent());
			int pid = f.getParentFile().getPath().split("\\\\").length - dir.getPath().split("\\\\").length;
			node.setPid(pid);
			FileInputStream fis = new FileInputStream(f);
			String md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis);
			fis.close();
			node.setMd5(md5);
			node.setLastModify(f.lastModified()+"");
			
			outFileTreeNodeList.add(node);
		}
		
			for (OutFileTreeNode node : outFileTreeNodeList) {// 全部的集合
					//JSONObject formDetailsJson = new JSONObject();
				//	formDetailsJson.put("id", node.getId());
					//formDetailsJson.put("name", node.getFileName());
					//formDetailsJson.put("p", node.getParent());
					//formDetailsJson.put("md5", node.getMd5());
					//formDetailsJson.put("lastModify", node.getLastModify());
					 JSON.toJSON(node);
					jsonArray.add(JSON.toJSON(node));
			}
			JSONObject json = new JSONObject();
			json.put("platformVersion", "1.0.1");
			json.put("list", jsonArray);
			responseDetailsJson.put(path, json);
			

		System.out.println(responseDetailsJson.toJSONString());
		
		 File outFile = new File(path+"/newFile.json");
		  try (FileOutputStream fop = new FileOutputStream(outFile)) {
		   // if file doesn't exists, then create it
		   if (!outFile.exists()) {
			   outFile.createNewFile();
		   }
		   // get the content in bytes
		   byte[] contentInBytes = responseDetailsJson.toJSONString().getBytes();
		   fop.write(contentInBytes);
		   fop.flush();
		   fop.close();
		   System.out.println("Done");
		  } catch (IOException e) {
		   e.printStackTrace();
		  }

	}
}
