package br.com.jcomputacao.nfe.danfe;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author marcilio
 */
public class DanfeHelperTest {
    
    public DanfeHelperTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of imprimir method, of class DanfeHelper.
     */
    @Test
    public void testImprimir() throws Exception {
        System.out.println("imprimir");
        String xml = "<nfeProc versao=\"3.10\" xmlns=\"http://www.portalfiscal.inf.br/nfe\"><NFe xmlns=\"http://www.portalfiscal.inf.br/nfe\"><infNFe versao=\"3.10\" Id=\"NFe35150605330872000131550010000114721000136843\"><ide><cUF>35</cUF><cNF>00013684</cNF><natOp>VENDA DE MERCADORIA</natOp><indPag>1</indPag><mod>55</mod><serie>1</serie><nNF>11472</nNF><dhEmi>2015-06-25T16:54:57-03:00</dhEmi><dhSaiEnt>2015-06-25T16:54:57-03:00</dhSaiEnt><tpNF>1</tpNF><idDest>1</idDest><cMunFG>3509502</cMunFG><tpImp>1</tpImp><tpEmis>1</tpEmis><cDV>3</cDV><tpAmb>1</tpAmb><finNFe>1</finNFe><indFinal>0</indFinal><indPres>9</indPres><procEmi>0</procEmi><verProc>v.2.0</verProc></ide><emit><CNPJ>05330872000131</CNPJ><xNome>TUDOR CAMPINAS BATERIAS LTDA.</xNome><xFant>TUDOR CAMPINAS BATERIAS LTDA.</xFant><enderEmit><xLgr>RUA COSMOPOLIS</xLgr><nro>30</nro><xBairro>JD. N.C.ELISEOS</xBairro><cMun>3509502</cMun><xMun>CAMPINAS</xMun><UF>SP</UF><CEP>13050540</CEP><cPais>1058</cPais><xPais>BRASIL</xPais><fone>1932673696</fone></enderEmit><IE>244921497117</IE><CRT>3</CRT></emit><dest><CNPJ>05437537000137</CNPJ><xNome>AUTO GERAL DE ITU LTDA.</xNome><enderDest><xLgr>AV OCTAVIANO PEREIRA MENDES,</xLgr><nro>1333</nro><xBairro>CENTRO</xBairro><cMun>3523909</cMun><xMun>ITU</xMun><UF>SP</UF><CEP>13301909</CEP><cPais>1058</cPais><fone>1140137777</fone></enderDest><indIEDest>1</indIEDest><IE>387034155115</IE></dest><det nItem=\"1\"><prod><cProd>00.03.0601220</cProd><cEAN>7896320561702</cEAN><xProd>BATERIA TFR60PVD</xProd><NCM>85071090</NCM><CFOP>5405</CFOP><uCom>UN</uCom><qCom>6.0000</qCom><vUnCom>199.0000000000</vUnCom><vProd>1194.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>6.0000</qTrib><vUnTrib>199.0000000000</vUnTrib><indTot>1</indTot><nFCI>15D15BE5-38D3-459C-A833-1257F0361682</nFCI></prod><imposto><ICMS><ICMS60><orig>5</orig><CST>60</CST></ICMS60></ICMS><IPI><cEnq>999</cEnq><IPITrib><CST>99</CST><vBC>0.00</vBC><pIPI>0.0000</pIPI><vIPI>0.00</vIPI></IPITrib></IPI><PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS></imposto></det><det nItem=\"2\"><prod><cProd>00.03.1502222</cProd><cEAN>7896320562075</cEAN><xProd>BATERIA THD150MVD</xProd><NCM>85071090</NCM><CFOP>5405</CFOP><uCom>UN</uCom><qCom>4.0000</qCom><vUnCom>468.0000000000</vUnCom><vProd>1872.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>4.0000</qTrib><vUnTrib>468.0000000000</vUnTrib><indTot>1</indTot><nFCI>67454823-2A3A-4C46-A38F-3D13E44E9B94</nFCI></prod><imposto><ICMS><ICMS60><orig>5</orig><CST>60</CST></ICMS60></ICMS><IPI><cEnq>999</cEnq><IPITrib><CST>99</CST><vBC>0.00</vBC><pIPI>0.0000</pIPI><vIPI>0.00</vIPI></IPITrib></IPI><PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS></imposto></det><det nItem=\"3\"><prod><cProd>00.03.0601080</cProd><cEAN>7896320561689</cEAN><xProd>BATERIA TFR60PHD</xProd><NCM>85071090</NCM><CFOP>5405</CFOP><uCom>UN</uCom><qCom>2.0000</qCom><vUnCom>199.0000000000</vUnCom><vProd>398.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>2.0000</qTrib><vUnTrib>199.0000000000</vUnTrib><indTot>1</indTot><nFCI>E1F81160-C139-4E5B-B535-D109BD9224B9</nFCI></prod><imposto><ICMS><ICMS60><orig>5</orig><CST>60</CST></ICMS60></ICMS><IPI><cEnq>999</cEnq><IPITrib><CST>99</CST><vBC>0.00</vBC><pIPI>0.0000</pIPI><vIPI>0.00</vIPI></IPITrib></IPI><PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS></imposto></det><det nItem=\"4\"><prod><cProd>00.03.0751040</cProd><cEAN>7896320561764</cEAN><xProd>BATERIA TFR75PDD</xProd><NCM>85071090</NCM><CFOP>5405</CFOP><uCom>UN</uCom><qCom>1.0000</qCom><vUnCom>316.0000000000</vUnCom><vProd>316.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>1.0000</qTrib><vUnTrib>316.0000000000</vUnTrib><indTot>1</indTot><nFCI>6A6A76D8-5B06-40D6-8C91-C47670902E04</nFCI></prod><imposto><ICMS><ICMS60><orig>5</orig><CST>60</CST></ICMS60></ICMS><IPI><cEnq>999</cEnq><IPITrib><CST>99</CST><vBC>0.00</vBC><pIPI>0.0000</pIPI><vIPI>0.00</vIPI></IPITrib></IPI><PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS></imposto></det><det nItem=\"5\"><prod><cProd>00.03.1052163</cProd><cEAN>7896320562044</cEAN><xProd>BATERIA THD105MPE</xProd><NCM>85071090</NCM><CFOP>5405</CFOP><uCom>UN</uCom><qCom>2.0000</qCom><vUnCom>368.0000000000</vUnCom><vProd>736.00</vProd><cEANTrib/><uTrib>UN</uTrib><qTrib>2.0000</qTrib><vUnTrib>368.0000000000</vUnTrib><indTot>1</indTot><nFCI>BDBB7A4F-923F-49D9-92ED-0F60A6E0CF37</nFCI></prod><imposto><ICMS><ICMS60><orig>5</orig><CST>60</CST></ICMS60></ICMS><IPI><cEnq>999</cEnq><IPITrib><CST>99</CST><vBC>0.00</vBC><pIPI>0.0000</pIPI><vIPI>0.00</vIPI></IPITrib></IPI><PIS><PISNT><CST>04</CST></PISNT></PIS><COFINS><COFINSNT><CST>04</CST></COFINSNT></COFINS></imposto></det><total><ICMSTot><vBC>0.00</vBC><vICMS>0.00</vICMS><vICMSDeson>0.00</vICMSDeson><vBCST>0.00</vBCST><vST>0.00</vST><vProd>4516.00</vProd><vFrete>0.00</vFrete><vSeg>0.00</vSeg><vDesc>0.00</vDesc><vII>0.00</vII><vIPI>0.00</vIPI><vPIS>0.00</vPIS><vCOFINS>0.00</vCOFINS><vOutro>0.00</vOutro><vNF>4516.00</vNF></ICMSTot></total><transp><modFrete>0</modFrete><transporta><CNPJ>05330872000131</CNPJ><xNome>TUDOR CAMPINAS BATERIAS LTDA ME.</xNome><IE>244921497117</IE><xEnder>RUA COSMOPOLIS, 30</xEnder><xMun>CAMPINAS</xMun><UF>SP</UF></transporta><vol><qVol>15</qVol><esp>BATERIAS</esp><pesoL>350.600</pesoL><pesoB>350.600</pesoB></vol></transp><cobr><fat><nFat>Nr.Fatura: 11472</nFat><vOrig>4516.00</vOrig><vLiq>4516.00</vLiq></fat><dup><nDup>11472/1</nDup><dVenc>2015-07-25</dVenc><vDup>1506.01</vDup></dup><dup><nDup>11472/2</nDup><dVenc>2015-08-24</dVenc><vDup>1505.00</vDup></dup><dup><nDup>11472/3</nDup><dVenc>2015-09-23</dVenc><vDup>1504.99</vDup></dup></cobr><infAdic><infCpl>DECLARAMOS QUE O(S) PRODUTO(S) ACIMA ESTA(AO) ADEQUADAMENTE EMBALADOS(S) / ACONDICIONADO(S) PARA SUPORTAR OS RISCOS NORMAIS DE CARREGAMENTO, DESCARREGAMENTO, TRANSBORDO E TRANSPORTE, CONFORME REGULAMENTACAO EM VIGOR, NO. DA ONU 2794 CLASSE 8 NO. DE RISCO 80. BATERIA(S) ELETRICA(S) UMIDA(S) CONTENDO ACIDO.  IMPOSTO RECOLHIDO POR SUBSTITUICAO TRIBUTARIA NOS TERMOS DO ARTIGO 313-0, DECRETO 45.490/2000 DO RICMS/SP.  DUPLICATA: 11472/1 25/07/2015 R$ 1.506,01 - 11472/2 24/08/2015 R$ 1.505,00 - 11472/3 23/09/2015 R$ 1.504,99</infCpl></infAdic></infNFe><Signature xmlns=\"http://www.w3.org/2000/09/xmldsig#\"><SignedInfo><CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/><SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"/><Reference URI=\"#NFe35150605330872000131550010000114721000136843\"><Transforms><Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"/><Transform Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"/></Transforms><DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"/><DigestValue>sUrebZ8TQt+Hn6m3iIGKrH9GdEU=</DigestValue></Reference></SignedInfo><SignatureValue>VkmUcpbINlvyqhXTDFAetvdGyH3ffbTTK6L2Np6MzYG6JiUD6o+zVeU38N013WlnH9CCfJcRnVU6nPTsIt62hYoNMhP8KyQZYKbE38Xl6GbAkYES4u79c5mYoaYs8jous8cg5kb1W3ubh/ugyburo1fBaQInf1LYU0zNCnlK2qmsvDBKRlQ8lGsbRPrXwt21FfpiuCNJfXFo7aB82s6KDS/YNvZxgUR8KwEDCHaU7IOj7rNMOoG7s41kSHYT+7yVi31R8Y0Q7kvbr6jrkMN1tX5MhfCoy0FtRh+xcSkmSwReEl7GmcCN2LVu6To4XvWwonHNwcFpJZZidJlfxACfhw==</SignatureValue><KeyInfo><X509Data><X509Certificate>MIIIUzCCBjugAwIBAgIQOIE6b4NBAYxwOWgzOC2vkzANBgkqhkiG9w0BAQsFADB0MQswCQYDVQQGEwJCUjETMBEGA1UEChMKSUNQLUJyYXNpbDEtMCsGA1UECxMkQ2VydGlzaWduIENlcnRpZmljYWRvcmEgRGlnaXRhbCBTLkEuMSEwHwYDVQQDExhBQyBDZXJ0aXNpZ24gTXVsdGlwbGEgRzUwHhcNMTUwNTE1MDAwMDAwWhcNMTgwNTEzMjM1OTU5WjCB3DELMAkGA1UEBhMCQlIxEzARBgNVBAoUCklDUC1CcmFzaWwxMTAvBgNVBAsUKEF1dGVudGljYWRvIHBvciBBUiBDIFJBTU9TIENFUlRJRklDQURPUkExGzAZBgNVBAsUEkFzc2luYXR1cmEgVGlwbyBBMzEVMBMGA1UECxQMSUQgLSA4Mjk3MDEwMSgwJgYDVQQDEx9UVURPUiBDQU1QSU5BUyBCQVRFUklBUyBMVERBIE1FMScwJQYJKoZIhvcNAQkBFhhuZmVAdHVkb3JjYW1waW5hcy5jb20uYnIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDDuMXijcaQVa9d+o7u0vd3Dow1CHXzuZ9acikg0zT4/3yYBRCtlHIGy4T4uECANMMLt+ukw/zT5L41j6yokLr6+ucBlr/SAoSav982S4G2F1hlaqQFc7e/wg/POxa4NW0MLSIpzMxPTwfHStMwTkFzuRmyK5yyvBDjpIk+5jtYIMUj/mwI3Aqru5J5GUsQJhcbv7XuJHEQcgaqa4uDtFXcfYPnRWItE9+t+i/uY40nhpr0AMh0tltvTKDFUtut4dnDfIc13wVbXnYWlIyqnK5ZNidn+MfhEAQurojxLvv6g8FMZcnR6ueoCxrvyf17zcRwWvhTT+4S4AkJ6d1DRUQNAgMBAAGjggN2MIIDcjCBvAYDVR0RBIG0MIGxoD0GBWBMAQMEoDQEMjA1MDQxOTgzMjIzNjc4NjU4MTcwMDAwMDAwMDAwMDAwMDAwMDAzNDAzMTE4MlNTUFNQoCIGBWBMAQMCoBkEF0FOREVSU09OIE1BUlFVRVMgTk9WQUVToBkGBWBMAQMDoBAEDjA1MzMwODcyMDAwMTMxoBcGBWBMAQMHoA4EDDAwMDAwMDAwMDAwMIEYbmZlQHR1ZG9yY2FtcGluYXMuY29tLmJyMAkGA1UdEwQCMAAwHwYDVR0jBBgwFoAUnVDPvf8kyq+xM+sX4kJ6jmkqjlMwDgYDVR0PAQH/BAQDAgXgMIGJBgNVHSAEgYEwfzB9BgZgTAECAwUwczBxBggrBgEFBQcCARZlaHR0cDovL2ljcC1icmFzaWwuY2VydGlzaWduLmNvbS5ici9yZXBvc2l0b3Jpby9kcGMvQUNfQ2VydGlzaWduX011bHRpcGxhL0RQQ19BQ19DZXJ0aVNpZ25NdWx0aXBsYS5wZGYwggElBgNVHR8EggEcMIIBGDBcoFqgWIZWaHR0cDovL2ljcC1icmFzaWwuY2VydGlzaWduLmNvbS5ici9yZXBvc2l0b3Jpby9sY3IvQUNDZXJ0aXNpZ25NdWx0aXBsYUc1L0xhdGVzdENSTC5jcmwwW6BZoFeGVWh0dHA6Ly9pY3AtYnJhc2lsLm91dHJhbGNyLmNvbS5ici9yZXBvc2l0b3Jpby9sY3IvQUNDZXJ0aXNpZ25NdWx0aXBsYUc1L0xhdGVzdENSTC5jcmwwW6BZoFeGVWh0dHA6Ly9yZXBvc2l0b3Jpby5pY3BicmFzaWwuZ292LmJyL2xjci9DZXJ0aXNpZ24vQUNDZXJ0aXNpZ25NdWx0aXBsYUc1L0xhdGVzdENSTC5jcmwwHQYDVR0lBBYwFAYIKwYBBQUHAwIGCCsGAQUFBwMEMIGgBggrBgEFBQcBAQSBkzCBkDBkBggrBgEFBQcwAoZYaHR0cDovL2ljcC1icmFzaWwuY2VydGlzaWduLmNvbS5ici9yZXBvc2l0b3Jpby9jZXJ0aWZpY2Fkb3MvQUNfQ2VydGlzaWduX011bHRpcGxhX0c1LnA3YzAoBggrBgEFBQcwAYYcaHR0cDovL29jc3AuY2VydGlzaWduLmNvbS5icjANBgkqhkiG9w0BAQsFAAOCAgEADCcfvlmysgwHUciHPBBdV4nMMPMDV2jfublNXeCS0lt8JdVI6XHetdyUHg7uas/+FchwMKZtu1O1cXX17f1hwJVEBU5e6cS1uprHKJfwgPQ1I/4IZpnNO2RmvzJ+2664y1V+rvxTB/leJhbdE6lsB2lwyw1GSQc1RVbACSQGl4hqa5HJBQhn3uhBILmYPhyefW34YjKfOw6Qi2KCVHqCj6HD7Q0mHYnSYNqlArxm1FaSKy7g2b6dCZaOAYRSCmR0tnnx2nk8vd1VtUI6yvvzha3VWnNDNxpVSTOhB8y+xdGYmh7UqUbaOFU9l+7x6wLnK65UxJx5qr73rS8axph/IXJ1bvWZuk7IHJJXIueLgTrsdunOrUQNKOFVyPeCWsGHwkQAPqbUMcf+lPsX6kqkgnt2PNpfRWw6MuIFH7P2kfiUBcUffQKXCX0K1yX15N5i2ucgmIo5vM2wti0xvvRqtr76WGAwidXwlgACqrG/jk9eHPzvYVIY5Dvt75C2ga2Za3EvlkHcYDtdqMabShDcRwuHyJoch1Xsv8IB/csqxX4cU94OpwB6/a2wZjcZ/LSYrQR4Gv+lFXp7nVQqJk3cRngrfJqohTvqxLCgtHXjZk+N2GN7aRN+zs0Qug02C941Q59UmvdXnINmQzEqxpJa6MMDF6fpvKO9IQRFVLu6us0=</X509Certificate></X509Data></KeyInfo></Signature></NFe>\n" +
                     "	<protNFe versao=\"3.10\"><infProt><tpAmb>1</tpAmb><verAplic>SP_NFE_PL_008f</verAplic><chNFe>35150605330872000131550010000114721000136843</chNFe><dhRecbto>2015-06-25T16:55:03-03:00</dhRecbto><nProt>135150390818446</nProt><digVal>sUrebZ8TQt+Hn6m3iIGKrH9GdEU=</digVal><cStat>100</cStat><xMotivo>Autorizado o uso da NF-e</xMotivo></infProt></protNFe></nfeProc>";
        
        List<NfeDanfe> nfes = new ArrayList<NfeDanfe>();
        JAXBContext context = JAXBContext.newInstance("br.inf.portalfiscal.nfe.xml.pl008f.nfes");
        Unmarshaller unmarshaller = context.createUnmarshaller();
        ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes("UTF-8"));
        br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc proc = unmarshaller.unmarshal(new StreamSource(bais), br.inf.portalfiscal.nfe.xml.pl008f.nfes.TNfeProc.class).getValue();
        bais.close();
        NfeDanfe danfe = new NfeDanfe(proc);
        nfes.add(danfe);
        DanfeHelper instance = new DanfeHelper();
        
        JasperPrint print = instance.imprimirNfe310(proc, null);
        
        File arquivo = new File("nfe.pdf");
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, arquivo);
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.exportReport();
    }

    
}
