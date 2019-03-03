package by.htp.basumatarau.normalCatalog.DAO.utl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.xml.sax.InputSource;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;

public class XjcEntityGenerator {
	private static final String schemaFile;
	private static final String targetPath;
	private static final String entityPackageName = "by.htp.basumatarau.normalCatalog.generatedEntities";
	
	static {
		schemaFile = XjcEntityGenerator.class
				.getClassLoader()
				.getResource("news.xsd")
				.getFile();
		targetPath = System.getProperty("user.dir") + "/src/main/java";
	}
	
	//uncomment and execute if xsd has been updated

	public static void main(String... args) {
		updateEntities();
	}

	
	private static void updateEntities(){
		try {
			System.out.println("entity generation...");
			XjcEntityGenerator.generateFromSchema(
					new File(schemaFile), entityPackageName, new File(targetPath));
			}catch (FileNotFoundException e) {
				System.out.println("Schema file " + schemaFile + " not found");
			}catch (IOException e) {
				System.out.println("IO exception while entity generation");
			}
	}
	
	public static JCodeModel generateFromSchema(final File schemaFile, final String packageName,
            final File targetPath) throws IOException, FileNotFoundException {

		
        final SchemaCompiler sc = XJC.createSchemaCompiler();
        final FileInputStream schemaStream = new FileInputStream(schemaFile);
        
        final InputSource is = new InputSource(schemaStream);
       
        is.setSystemId(schemaFile.toURI().toString());

        sc.parseSchema(is);
        sc.forcePackageName(packageName);

        final S2JJAXBModel s2 = sc.bind();
        final JCodeModel jcm = s2.generateCode(null, null);
        
        s2.generateCode(null, null);
        
        try (PrintStream status = new PrintStream(new ByteArrayOutputStream())) {
            jcm.build(targetPath, status);
        }

        return jcm;
    }
}
