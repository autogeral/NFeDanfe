package br.com.jcomputacao.nfe.danfe;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 22/09/2010 15:37:59
 * @author Murilo
 */
public class DanfeHelper {
    
    public JasperPrint imprimir(br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc proc, Image logotipo) throws JRException, IOException {
        List<br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc> l = new ArrayList<br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc>();
        l.add(proc);
        return imprimir(l, logotipo);
    }

    public JasperPrint imprimir(List<br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc> procs, Image logotipo) throws JRException, IOException {
        InputStream is = null;
        InputStream isi = null;
        InputStream isic = null;
        try {
            String jasper = "/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeRetratoA4Report.jasper";
            is   = this.getClass().getResource(jasper).openStream();
            if(is==null) {
                System.out.println("Nao consegui ler o arquivo : "+jasper);
                return null;
            }
            
            jasper = "/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeItemRetratoA4Report.jasper";
            isi  = this.getClass().getResource(jasper).openStream();
            if(isi==null) {
                System.out.println("Nao consegui ler o arquivo : "+jasper);
                return null;
            }

            jasper = "/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeInfComplRetratoA4Report.jasper";
            isic = this.getClass().getResource(jasper).openStream();
            if(isic==null) {
                System.out.println("Nao consegui ler o arquivo : "+jasper);
                return null;
            }


            JasperReport report = (JasperReport) JRLoader.loadObject(is);
            JasperReport detail = (JasperReport) JRLoader.loadObject(isi);
            JasperReport cmplto = (JasperReport) JRLoader.loadObject(isic);

            Map parametros = new HashMap();
            parametros.put("ImpressaoDanfeItemRetratoA4Report_subreport", detail);
            parametros.put("ImpressaoDanfeInfComplRetratoA4Report_subreport", cmplto);
            parametros.put("msgVersaoTeste", "");
            parametros.put("Frente", "");
            parametros.put("PreImpresso", Boolean.FALSE);


            List<NfeDanfe> nfes = new ArrayList<NfeDanfe>();
            for(br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc proc:procs) {
                NfeDanfe danfe = new NfeDanfe(proc);
                danfe.setEmitenteLogoTipo(logotipo);
                nfes.add(danfe);
            }

            DanfeDatasource dd = new DanfeDatasource(nfes);
            //do jeito que esta so irao sair os itens da primeira nota (sempre)
//            DanfeItemDatasource did = new DanfeItemDatasource(danfe.getItens());
//            parametros.put("ImpressaoDanfeInfComplRetratoA4Report_datasource", did.getDataSource());
//            parametros.put("ImpressaoDanfeItemRetratoA4Report_datasource", did.getDataSource());
            return JasperFillManager.fillReport(report, parametros, dd.getDataSource());
        } finally {
            if(is  !=null) is.close();
            if(isi !=null) isi.close();
            if(isic!=null) isic.close();
        }
    }
    
    public JasperPrint imprimirNfe310(br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc proc, Image logotipo) throws JRException, IOException {
        List<br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc> l = new ArrayList<br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc>();
        l.add(proc);
        return imprimirNfe310(l, logotipo);
    }

    public JasperPrint imprimirNfe310(List<br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc> procs, Image logotipo) throws JRException, IOException {
        InputStream is = null;
        InputStream isi = null;
        InputStream isic = null;
        try {
            String jasper = "/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeRetratoA4Report.jasper";
            is   = this.getClass().getResource(jasper).openStream();
            if(is==null) {
                System.out.println("Nao consegui ler o arquivo : "+jasper);
                return null;
            }
            
            jasper = "/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeItemRetratoA4Report.jasper";
            isi  = this.getClass().getResource(jasper).openStream();
            if(isi==null) {
                System.out.println("Nao consegui ler o arquivo : "+jasper);
                return null;
            }

            jasper = "/br/com/jcomputacao/nfe/danfe/ImpressaoDanfeInfComplRetratoA4Report.jasper";
            isic = this.getClass().getResource(jasper).openStream();
            if(isic==null) {
                System.out.println("Nao consegui ler o arquivo : "+jasper);
                return null;
            }


            JasperReport report = (JasperReport) JRLoader.loadObject(is);
            JasperReport detail = (JasperReport) JRLoader.loadObject(isi);
            JasperReport cmplto = (JasperReport) JRLoader.loadObject(isic);

            Map parametros = new HashMap();
            parametros.put("ImpressaoDanfeItemRetratoA4Report_subreport", detail);
            parametros.put("ImpressaoDanfeInfComplRetratoA4Report_subreport", cmplto);
            parametros.put("msgVersaoTeste", "");
            parametros.put("Frente", "");
            parametros.put("PreImpresso", Boolean.FALSE);


            List<NfeDanfe> nfes = new ArrayList<NfeDanfe>();
            for(br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc proc:procs) {
                NfeDanfe danfe = new NfeDanfe(proc);
                danfe.setEmitenteLogoTipo(logotipo);
                nfes.add(danfe);
            }

            DanfeDatasource dd = new DanfeDatasource(nfes);
            //do jeito que esta so irao sair os itens da primeira nota (sempre)
//            DanfeItemDatasource did = new DanfeItemDatasource(danfe.getItens());
//            parametros.put("ImpressaoDanfeInfComplRetratoA4Report_datasource", did.getDataSource());
//            parametros.put("ImpressaoDanfeItemRetratoA4Report_datasource", did.getDataSource());
            return JasperFillManager.fillReport(report, parametros, dd.getDataSource());
        } finally {
            if(is  !=null) is.close();
            if(isi !=null) isi.close();
            if(isic!=null) isic.close();
        }
    }
}
