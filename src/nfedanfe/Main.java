/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nfedanfe;

import br.com.jcomputacao.nfe.danfe.DanfeDatasource;
import br.com.jcomputacao.nfe.danfe.DanfeItemDatasource;
import br.com.jcomputacao.nfe.danfe.NfeDanfe;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 * 10/07/2010 22:22:28
 * @author Murilo
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws JRException, IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        if (args.length == 0) {
            System.out.println("Especifique o caminho para o arquivo");
            return;
        } else {
            File f = new File(args[0]);
            if (f.exists() && f.isFile()) {
                FileReader reader = new FileReader(f);
                BufferedReader br = new BufferedReader(reader);
                String aux = null;
                while ((aux = br.readLine()) != null) {
                    sb.append(aux);
                }
                br.close();
                reader.close();
            } else {
                System.out.println("Arquivo " + f.getAbsolutePath() + " nao encontrado");
                return;
            }
        }

        String xml = sb.toString();
        JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        TNfeProc proc = unmarshaller.unmarshal(new StreamSource(bais), TNfeProc.class).getValue();
        bais.close();

        Map parametros = new HashMap();

        JasperPrint impressao = null;
        String jasperFile = "br/com/jcomputacao/nfe/danfe/ImpressaoDanfeRetratoA4Report.jasper";
        //JasperReport report = (JasperReport) net.sf.jasperreports.engine.util.JRLoader.loadObject(new java.io.FileInputStream(jasperFile));
        jasperFile = "src/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeRetratoA4Report.jrxml";
        File f = new File(jasperFile);
        if (!f.exists()) {
            System.out.println("Nao existe : " + f.getAbsolutePath());
            return;
        }

        JasperDesign jasperDesign = JRXmlLoader.load(jasperFile);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
//        InputStream jasper = jasperFile.getClass().getResourceAsStream(jasperFile);

        jasperFile = "src/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeItemRetratoA4Report.jrxml";
        f = new File(jasperFile);
        if (!f.exists()) {
            System.out.println("Nao existe : " + f.getAbsolutePath());
            return;
        }
        jasperDesign = JRXmlLoader.load(jasperFile);
        JasperReport subReportDetalhes = JasperCompileManager.compileReport(jasperDesign);
        parametros.put("ImpressaoDanfeItemRetratoA4Report_subreport", subReportDetalhes);

        jasperFile = "src/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeInfComplRetratoA4Report.jrxml";
        f = new File(jasperFile);
        if (!f.exists()) {
            System.out.println("Nao existe : " + f.getAbsolutePath());
            return;
        }
        jasperDesign = JRXmlLoader.load(jasperFile);
        JasperReport subReportDetalhesInfCompl = JasperCompileManager.compileReport(jasperDesign);
        parametros.put("ImpressaoDanfeInfComplRetratoA4Report_subreport", subReportDetalhesInfCompl);

//        List<NfeDanfeItem> nfesItens = new ArrayList<NfeDanfeItem>();
//        JRBeanCollectionDataSource itemDatasource = new JRBeanCollectionDataSource(nfesItens);
//        parametros.put("ImpressaoDanfeItemRetratoA4Report_datasource", itemDatasource);
//        parametros.put("ImpressaoDanfeInfComplRetratoA4Report_datasource", itemDatasource);
        parametros.put("msgVersaoTeste", "");
        parametros.put("Frente", "");
        parametros.put("PreImpresso", Boolean.FALSE);
        Image logotipo = null;

        jasperFile = "xmls//logoAutoGeral.jpg";
        f = new File(jasperFile);
        if (f.exists()) {
            logotipo = ImageIO.read(f);
        } else {
            System.out.println("Arquivo de logotipo nao existe : " + f.getAbsolutePath());
        }

        List<NfeDanfe> nfes = new ArrayList<NfeDanfe>();
        NfeDanfe danfe = new NfeDanfe(proc);
        danfe.setEmitenteLogoTipo(logotipo);
        nfes.add(danfe);
        DanfeDatasource dd = new DanfeDatasource(nfes);
        //do jeito que esta so irao sair os itens da primeira nota (sempre)
        DanfeItemDatasource did = new DanfeItemDatasource(danfe.getItens());
        parametros.put("ImpressaoDanfeInfComplRetratoA4Report_datasource", did.getDataSource());
        parametros.put("ImpressaoDanfeItemRetratoA4Report_datasource", did.getDataSource());

        //        javax.swing.ImageIcon logotipo = new javax.swing.ImageIcon(
        //                jasperFile.getClass().getResource("/nfedanfe/brasao.gif"));
//        Image logotipo = Toolkit.getDefaultToolkit().getImage("/nfedanfe/brasao.gif");
//        danfe.setEmitenteLogoTipo(logotipo);
//        danfe.setCodigoBarras(logotipo);

        //impressao = JasperFillManager.fillReport(jasper, parametros, jrbds);
        impressao = JasperFillManager.fillReport(jasperReport, parametros, dd.getDataSource());

//        JRDataSource ds = null;
//        impressao = JasperFillManager.fillReport(jasperReport, parametros, dd);
        String nomeArquivo = "danfe.pdf";
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, nomeArquivo);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, impressao);
        exporter.exportReport();

//        jasper.close();
//        subReportDetalhes.close();
//        subReportDetalhesInfCompl.close();
    }
}
