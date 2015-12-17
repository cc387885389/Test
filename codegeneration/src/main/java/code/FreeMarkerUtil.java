package code;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.Version;

public class FreeMarkerUtil {

	/**
	 * 
	 * @param data
	 *            模板数据
	 * @param outPutPath
	 *            文件输出路径
	 * @param outPutFileName
	 *            输出文件文件名
	 * @param templatePath
	 *            模板文件夹
	 * @param templateName
	 *            模板名称
	 */
	public static void createTemplate(Object data, String outPutPath,
			String outPutFileName, String templatePath, String templateName) {
		Configuration cfg = new Configuration(new Version(2, 3, 0));
		Writer writer = null;
		try {
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			cfg.setDefaultEncoding("utf-8");
			createOutPutPath(outPutPath);
			Template template = cfg.getTemplate(templateName);
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
					new File(outPutPath, outPutFileName)),"utf-8"));
			template.process(data, writer);
		} catch (IOException e) {
			throw new RuntimeException("模板加载失败！ " + e.getMessage());
		} catch (TemplateException e) {
			throw new RuntimeException("模板生成失败！ " + e.getMessage());
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void createOutPutPath(final String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}
}
