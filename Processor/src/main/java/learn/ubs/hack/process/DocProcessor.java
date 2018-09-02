package  learn.ubs.hack.process;

import learn.ubs.hack.base.Profile;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.IOException;

public class DocProcessor extends ProfileProcessor{

    public Profile processProfile(File profile) throws InvalidFormatException, IOException {
//        FileInputStream fileInputStream  = FileInputStream (profile);
        XWPFDocument xwpfDocument = new XWPFDocument(OPCPackage.open(profile));
        return  null;
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        DocProcessor docProcessor = new DocProcessor();
        File file = new File("C:\\Users\\balajisira\\Desktop\\DESK\\SujathaSira_CV.pdf.doc");
        docProcessor.processProfile(file);

    }
}
