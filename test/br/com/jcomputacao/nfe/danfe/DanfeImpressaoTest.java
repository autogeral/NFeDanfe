package br.com.jcomputacao.nfe.danfe;

import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc;
import com.lowagie.text.DocumentException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author WILL
 */
public class DanfeImpressaoTest {

    public DanfeImpressaoTest() {
    }

    public static void main(String a[]) {
        String dir = "exemplos/";
        File fdir = new File(dir);
        if (!fdir.exists()) {
            System.out.println("Diretório não encontrado : " + fdir.getAbsolutePath());
            System.out.flush();
            return;
        }
        File[] files = fdir.listFiles(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return (name.toLowerCase().endsWith(".xml"));
            }
        });

        List<TNfeProc> procs = new ArrayList<TNfeProc>();
        for (File f : files) {
            System.out.println("Tratando : " + f.getAbsolutePath());
            try {
                JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe.xml.pl006q.nfes");
                Unmarshaller unmarshaller = context.createUnmarshaller();
                FileInputStream fis = new FileInputStream(f);
                TNfeProc proc = unmarshaller.unmarshal(new StreamSource(fis), TNfeProc.class).getValue();
                fis.close();

                if (proc != null) {
                    procs.add(proc);
                }
            } catch (IOException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
            } catch (JAXBException ex) {
                Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, null, ex);
            }
        }

        DanfeImpressaoPdf di = new DanfeImpressaoPdf(procs);
        try {
            ByteArrayOutputStream resultado = di.execute();
            FileOutputStream fos = new FileOutputStream("notas.pdf");
            fos.write(resultado.toByteArray());
            fos.close();
            resultado.close();
        } catch (DocumentException ex) {
            Logger.getLogger(DanfeImpressaoTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DanfeImpressaoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
