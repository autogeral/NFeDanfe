/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jcomputacao.nfe.danfe;

import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 11/07/2010 13:39:46
 * @author Murilo
 */
public class DanfeDatasource extends JRAbstractBeanDataSourceProvider {
    private List<NfeDanfe> list = null;

    public DanfeDatasource () {
        super(NfeDanfe.class);
        try {
            list = new ArrayList<NfeDanfe>();
            String nfeString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><nfeProc versao=\"1.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" Id=\"NFe35100509346052000199550010000000030500410263\" versao=\"1.10\"><ide><cUF>35</cUF><cNF>050041026</cNF><natOp>PRESTACAO DE SERVICO TRIBUTADO PELO ISSQN</natOp><indPag>0</indPag><mod>55</mod><serie>1</serie><nNF>3</nNF><dEmi>2010-05-06</dEmi><dSaiEnt>2010-05-06</dSaiEnt><tpNF>1</tpNF><cMunFG>3523909</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>3</cDV><tpAmb>1</tpAmb><finNFe>1</finNFe><procEmi>3</procEmi><verProc>1.4.1</verProc></ide><emit><CNPJ>09346052000199</CNPJ><xNome>J. COMPUTACAO LTDA ME</xNome><xFant>J. COMPUTACAO</xFant><enderEmit><xLgr>RUA MARECHAL DEODORO</xLgr><nro>505</nro><xCpl>AP132</xCpl><xBairro>CHAFARIZ</xBairro><cMun>3523909</cMun><xMun>Itu</xMun><UF>SP</UF><CEP>13300110</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>1140228588</fone></enderEmit><IE>387191599112</IE><IM>19355</IM><CNAE>4751200</CNAE></emit><dest><CNPJ>00610742000139</CNPJ><xNome>CIPEC AUTOPECAS LTDA</xNome><enderDest><xLgr>AL. COMENDADOR DR. SANTORO MIRONE</xLgr><nro>730</nro><xBairro>DISTR. INDAL. JOAO NAREZI</xBairro><cMun>3520509</cMun><xMun>Indaiatuba</xMun><UF>SP</UF><CEP>13347300</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>1938349822</fone></enderDest><IE>353196301111</IE></dest><det nItem=\"1\"><prod><cProd>3</cProd><cEAN/><xProd>PRESTACAO DE SERVICOS DE INFORMATICA</xProd><CFOP>5933</CFOP><uCom>HR</uCom><qCom>8.0000</qCom><vUnCom>80.0000</vUnCom><vProd>640.00</vProd><cEANTrib/><uTrib>HR</uTrib><qTrib>8.0000</qTrib><vUnTrib>80.0000</vUnTrib></prod><imposto><ICMS><ICMS40><orig>0</orig><CST>41</CST></ICMS40></ICMS><IPI><cEnq>999</cEnq><IPINT><CST>53</CST></IPINT></IPI><PIS><PISAliq><CST>01</CST><vBC>640.00</vBC><pPIS>0.65</pPIS><vPIS>4.16</vPIS></PISAliq></PIS><COFINS><COFINSAliq><CST>01</CST><vBC>640.00</vBC><pCOFINS>3.00</pCOFINS><vCOFINS>19.20</vCOFINS></COFINSAliq></COFINS><ISSQN><vBC>640.00</vBC><vAliq>5.00</vAliq><vISSQN>32.00</vISSQN><cMunFG>3523909</cMunFG><cListServ>106</cListServ></ISSQN></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vBCST>0.00</vBCST><vST>0.00</vST><vProd>640.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vPIS>4.16</vPIS><vCOFINS>19.20</vCOFINS><vOutro>0.00</vOutro><vNF>640.00</vNF></ICMSTot><ISSQNtot><vBC>640.00</vBC><vISS>32.00</vISS><vPIS>4.16</vPIS><vCOFINS>24.90</vCOFINS></ISSQNtot></total><transp><modFrete>0</modFrete></transp></infNFe><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe35100509346052000199550010000000030500410263\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>IHTmlk0RYQCCOvaM3Lso5lEvHTM=</DigestValue></Reference></SignedInfo><SignatureValue>rd7fjioFb69WJk2b1Vw1fZW4ECVoZkxCOWM96+QoECJGgT/AsKqyDZZcsLPSOGSayiDaU/cOZVO1\n" + "/8XFQ4CenBOEoaXtN+VhQA0kf6MR25qOyj4SuTE4IRpn9Vup0VdZ6Hwr8SIflIFnCbTBZvz4Dq9B\n" + "Jw+WkJhz9+lklUpBJBo=</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIGJjCCBQ6gAwIBAgIIH3/eZxA7ErUwDQYJKoZIhvcNAQEFBQAwTDELMAkGA1UEBhMCQlIxEzAR\n" + "BgNVBAoTCklDUC1CcmFzaWwxKDAmBgNVBAMTH1NFUkFTQSBDZXJ0aWZpY2Fkb3JhIERpZ2l0YWwg\n" + "djEwHhcNMTAwNDIwMjAxNzAwWhcNMTMwNDE5MjAxNzAwWjCB3zELMAkGA1UEBhMCQlIxEzARBgNV\n" + "BAoTCklDUC1CcmFzaWwxFDASBgNVBAsTCyhFTSBCUkFOQ08pMRgwFgYDVQQLEw8wMDAwMDEwMDEw\n" + "MDM0NTAxFDASBgNVBAsTCyhFTSBCUkFOQ08pMRQwEgYDVQQLEwsoRU0gQlJBTkNPKTEUMBIGA1UE\n" + "</X509Certificate></X509Data></KeyInfo></Signature></NFe><protNFe versao=\"1.10\"><infProt><tpAmb>1</tpAmb><verAplic>SP_NFE_PL_005e</verAplic><chNFe>35100509346052000199550010000000030500410263</chNFe><dhRecbto>2010-05-10T14:07:18</dhRecbto><nProt>135100335264889</nProt><digVal>IHTmlk0RYQCCOvaM3Lso5lEvHTM=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe></nfeProc>";
            JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe.xml.pl006q.nfes");
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ByteArrayInputStream bais = new ByteArrayInputStream(nfeString.getBytes("UTF-8"));
            TNfeProc rcr = unmarshaller.unmarshal(new StreamSource(bais), TNfeProc.class).getValue();
            bais.close();

            if (rcr != null) {
                System.out.println("NFeProcessamento");
                NfeDanfe danfe = new NfeDanfe(rcr);
                list.add(danfe);
            }
        } catch (Exception ex) {
            Logger.getLogger(DanfeDatasource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public DanfeDatasource(List<NfeDanfe> danfes) {
        super(NfeDanfe.class);
        this.list = danfes;
    }

    public JRDataSource create(JasperReport arg0) throws JRException {
        return new JRBeanCollectionDataSource(list);
    }

    public void dispose(JRDataSource arg0) throws JRException {

    }

    public JRBeanCollectionDataSource getDataSource(){
        return new JRBeanCollectionDataSource(this.list);
    }

}
