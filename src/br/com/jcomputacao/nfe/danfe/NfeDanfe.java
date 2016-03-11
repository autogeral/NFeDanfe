package br.com.jcomputacao.nfe.danfe;


import br.com.jcomputacao.nfe.danfe.util.StringUtil;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import net.sourceforge.barbecue.Barcode;
import net.sourceforge.barbecue.BarcodeException;
import net.sourceforge.barbecue.BarcodeImageHandler;
import net.sourceforge.barbecue.output.OutputException;

/**
 * 11/07/2010 13:24:21
 * @author Murilo
 */
public class NfeDanfe {
    private String numeroNfe;
    private String serieNfe;
    private java.awt.Image codigoBarras;
    private java.awt.Image emitenteLogoTipo;
    private String saidaEntrada;
    private String naturezaOperacao;
    private String chaveAcesso;
    private String dataEmissao;
    private String dataSaidaEntrada;
    private String horaSaida;
    private String emitenteRazaoSocial;
    private String emitenteEndereco;
    private String emitenteBairro;
    private String emitenteMunicipio;
    private String emitenteUf;
    private String emitenteInscricaoEstadual;
    private String emitenteInscricaoEstadualSubtributario;
    private String emitenteCnpj;
    private String emitenteCep;
    private String emitenteTelefone;
    private String destinatarioRazaoSocial;
    private String destinatarioCnpjCpf;
    private String destinatarioEndereco;
    private String destinatarioBairro;
    private String destinatarioCep;
    private String destinatarioMunicipio;
    private String destinatarioFoneFax;
    private String destinatarioUf;
    private String destinatarioInscricaoEstadual;
    private String fatura;
    private String baseIcms;
    private String valorIcms;
    private String baseIcmsSubstituicao;
    private String valorIcmsSubstituicao;
    private String valorTotalProduto;
    private String valorFrete;
    private String valorSeguro;
    private String desconto;
    private String outrasDespesas;
    private String valorIpi;
    private String valorTotalNota;
    private String transportadorRazaoSocial;
    private String transportadorFretePorConta;
    private String transportadorCodigoAntt;
    private String transportadorPlacaVeiculo;
    private String transportadorUfVeiculo;
    private String transportadorCnpjCpf;
    private String transportadorEndereco;
    private String transportadorMunicipio;
    private String transportadorUf;
    private String transportadorInscricaoEstadual;
    private String transportadorQuantidade;
    private String transportadorEspecie;
    private String transportadorMarca;
    private String transportadorNumeracao;
    private String transportadorPesoBruto;
    private String transportadorPesoLiquido;
    private String inscricaoMunicipal;
    private String valorTotalServicos;
    private String baseIssqn;
    private String valorIssqn;
    private String informacoesComplementares;
    private String tipoEmissao;
    private String informacoesComplementares2;
    private String dadosNfe;
    private java.awt.Image codigoBarrasContingencia;
    private String protocoloDataAutorizacao;
    private String codigoTipoEmissao;
    private String numeroRegistroDPEC;
    private String dataRegistroDPEC;
    private String situacao;
    private String msgAutenticidade;
    private List<NfeDanfeItem> itens = new ArrayList<NfeDanfeItem>();
    private String valorPis;
    private String valorCofins;
    private final int versao;

    public NfeDanfe(br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNfeProc proc) {
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe nfe = proc.getNFe();
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe inf = nfe.getInfNFe();
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Ide id = inf.getIde();
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMinimumIntegerDigits(9);
        nf.setGroupingUsed(true);
        Long longNNfe = new Long(id.getNNF());
        versao = 310;
        numeroNfe = nf.format(longNNfe);
        serieNfe = id.getSerie();
        saidaEntrada = id.getTpNF();
        naturezaOperacao = id.getNatOp();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        DateFormat bdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            String emissao = id.getDhEmi();
            if (!StringUtil.isNull(emissao)) {
                dataEmissao = bdf.format(sdf.parse(id.getDhEmi()));
            }
            if (id.getDhSaiEnt() != null) {
                dataSaidaEntrada = bdf.format(sdf.parse(id.getDhSaiEnt()));
            }
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex);
        }


//        proc.getProtocolo().getInformacoes().getNumeroProtocolo();
//        proc.getProtocolo().getInformacoes().getDataHoraRecebimento();
//        proc.getProtocolo().getInformacoes().getTipoAmbiente();GHH
//        proc.getProtocolo().getInformacoes().getDataHoraRecebimento();
        situacao = proc.getProtNFe().getInfProt().getCStat();
        if("100".equals(situacao)) {
            situacao = "AUTORIZADA";
        } else if ("101".equals(situacao) || "135".equals(situacao)) {
            situacao = "CANCELADA";
        } else if ("102".equals(situacao)) {
            situacao = "INUTILIZADA";
        }
        protocoloDataAutorizacao = proc.getProtNFe().getInfProt().getNProt();
        protocoloDataAutorizacao += " - ";        
        protocoloDataAutorizacao += proc.getProtNFe().getInfProt().getDhRecbto().toString();

        msgAutenticidade = "Consulta de autenticidade no portal nacional da "
                         + "NF-e www.nfe.fazenda.gov.br/portal ou no site "
                         + "da Sefaz Autorizadora";

        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Emit emit = inf.getEmit();
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Dest dest = inf.getDest();
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Total tot = inf.getTotal();

        inscricaoMunicipal = emit.getIM();

        horaSaida = (id.getDhSaiEnt() != null ? id.getDhSaiEnt().substring(11, 19) : "");
        emitenteRazaoSocial = StringUtil.htmlIso8859decode(emit.getXNome());
        emitenteEndereco = StringUtil.htmlIso8859decode(emit.getEnderEmit().getXLgr());
        if(emit.getEnderEmit().getNro()!=null && !"".equals(emit.getEnderEmit().getNro())) {
            emitenteEndereco += ", "+emit.getEnderEmit().getNro();
        }
        emitenteBairro = StringUtil.htmlIso8859decode(emit.getEnderEmit().getXBairro());
        emitenteMunicipio = emit.getEnderEmit().getXMun();
        emitenteUf = emit.getEnderEmit().getUF().toString();
        emitenteInscricaoEstadual = emit.getIE();
        emitenteInscricaoEstadualSubtributario = emit.getIEST();
        emitenteCnpj = emit.getCNPJ();
        emitenteCep = emit.getEnderEmit().getCEP();
        
        emitenteTelefone = formataTelefone(emit.getEnderEmit().getFone());
        if(emitenteTelefone!=null) {
            emitenteCep += "\n";
        }
        destinatarioRazaoSocial = StringUtil.htmlIso8859decode(dest.getXNome());
        destinatarioCnpjCpf = (dest.getCNPJ()==null || "".equals(dest.getCNPJ())?dest.getCPF():dest.getCNPJ());
        destinatarioEndereco = soPrimeiraMaiuscula(StringUtil.htmlIso8859decode(dest.getEnderDest().getXLgr()));
        if (null != dest.getEnderDest().getNro() && !"".equals(dest.getEnderDest().getNro())
                && !"0".equals(dest.getEnderDest().getNro())) {
            destinatarioEndereco += ", " + dest.getEnderDest().getNro();
        }
        if(null != dest.getEnderDest().getXCpl() && !"".equals(dest.getEnderDest().getXCpl())){
            destinatarioEndereco += ", " + soPrimeiraMaiuscula(StringUtil.htmlIso8859decode(dest.getEnderDest().getXCpl()));
        }
        destinatarioBairro = StringUtil.htmlIso8859decode(dest.getEnderDest().getXBairro());
        destinatarioCep = dest.getEnderDest().getCEP();
        destinatarioMunicipio = dest.getEnderDest().getXMun();
        destinatarioFoneFax = dest.getEnderDest().getFone();
        destinatarioUf = dest.getEnderDest().getUF().toString();
        destinatarioInscricaoEstadual = dest.getIE();
        String indPag = inf.getIde().getIndPag();
        
        if("0".equals(indPag)) {
            fatura = "pagamento à vista";
        } else if("1".equals(indPag)) {
            fatura = "À prazo";
        } else if("2".equals(indPag)) {
            fatura = "outros.";
        }
        boolean insereCobrancaXmlAtravezNfesPagamentosParcelas = Boolean.parseBoolean(System.getProperty("insere.cobrancaXml.atravez.nfesPagamentosParcelas", "false"));
        if (inf.getCobr() != null && inf.getCobr().getDup() != null) {
            List<br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Cobr.Dup> dups = inf.getCobr().getDup();
            boolean first = true;
            for (br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Cobr.Dup dup : dups) {
                String dupVencimento = "";
                if (dup != null && dup.getDVenc() != null && dup.getDVenc().length() > 8) {
                    dupVencimento = dup.getDVenc().substring(8)
                            + "/" + dup.getDVenc().substring(5, 7)
                            + "/" + dup.getDVenc().substring(2, 4);
                }
                if(insereCobrancaXmlAtravezNfesPagamentosParcelas) {
                    if(first) {
                        fatura += " Venc.: ";
                        first = false;
                    }
                    fatura += dupVencimento + " R$" + dup.getVDup().replace(".", ",") + " ";
                } else {
                    fatura += " Dup." + dup.getNDup() + " " + dupVencimento + " $" + dup.getVDup().replace(".", ",");
                }                
            }
        }
        baseIcms = tot.getICMSTot().getVBC();
        valorIcms = tot.getICMSTot().getVICMS();
        baseIcmsSubstituicao = tot.getICMSTot().getVBCST();
        valorIcmsSubstituicao = tot.getICMSTot().getVST();
        valorTotalProduto = tot.getICMSTot().getVProd();
        valorFrete = tot.getICMSTot().getVFrete();
        valorSeguro = tot.getICMSTot().getVSeg();
        desconto = tot.getICMSTot().getVDesc();
        outrasDespesas = tot.getICMSTot().getVOutro();
        valorIpi = tot.getICMSTot().getVIPI();
        valorTotalNota = tot.getICMSTot().getVNF();
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Transp transporte = inf.getTransp();
        if (transporte != null) {
            if (transporte.getTransporta() != null) {
                transportadorRazaoSocial = StringUtil.htmlIso8859decode(transporte.getTransporta().getXNome());
                transportadorCnpjCpf = transporte.getTransporta().getCNPJ();
                transportadorEndereco = StringUtil.htmlIso8859decode(transporte.getTransporta().getXEnder());
                transportadorMunicipio = transporte.getTransporta().getXMun();
                if (null != transporte.getTransporta().getUF()) {
                    transportadorUf = transporte.getTransporta().getUF().toString();
                }
                transportadorInscricaoEstadual = transporte.getTransporta().getIE();
            }
            transportadorFretePorConta = transporte.getModFrete();
            if (transporte.getVeicTransp() != null) {
                transportadorCodigoAntt = transporte.getVeicTransp().getRNTC();
                transportadorPlacaVeiculo = transporte.getVeicTransp().getPlaca();
                transportadorUfVeiculo = transporte.getVeicTransp().getUF().toString();

            }
        }
        
        if(transporte.getVol() != null && !transporte.getVol().isEmpty()){
            List<br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Transp.Vol> volumes = transporte.getVol();
            for(br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Transp.Vol volume:volumes){
                transportadorPesoBruto = volume.getPesoB();
                transportadorPesoLiquido = volume.getPesoL();
                transportadorEspecie = volume.getEsp();
                transportadorMarca = volume.getMarca();
                transportadorQuantidade = volume.getQVol();
                transportadorNumeracao = volume.getNVol();
            }
        }

        if (tot != null) {
            br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Total.ISSQNtot issqn = tot.getISSQNtot();
            if (issqn != null) {
                valorTotalServicos = issqn.getVServ();
                valorPis    = issqn.getVPIS();
                valorCofins = issqn.getVCOFINS();
                baseIssqn   = issqn.getVBC();
                valorIssqn  = issqn.getVISS();
            }
        }
        tipoEmissao = id.getTpEmis();
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.InfAdic infAd = inf.getInfAdic();
        if (infAd != null) {
            informacoesComplementares = infAd.getInfCpl();
            if(informacoesComplementares==null) {
                informacoesComplementares = "";
            }
                
            informacoesComplementares2 = infAd.getInfAdFisco();
            if (informacoesComplementares2 != null) {
                if(!"".equals(informacoesComplementares)){
                    informacoesComplementares += "\n";
                }
                informacoesComplementares += "Informações Adicionais de Interesse do Fisco: ";
                informacoesComplementares += informacoesComplementares2;
            }
        }

        chaveAcesso = inf.getId().replace("NFe", "");
        StringBuilder sb = new StringBuilder();
        int limit = 10;
        for(int i=0;i<=limit;i++) {
            int j = i*4;
            sb.append(chaveAcesso.substring(j, j+4));
            if(i<limit) {
                sb.append(" ");
            }
        }

        chaveAcesso = sb.toString();
        /**
         *
         * O Código de Barras Adicional dos Dados da NF-e será formado pelo seguinte conteúdo, em
um total de 36 caracteres:
cUF tpEmis CNPJ vNF ICMSp ICMSs DD DV
Quantidade de caracteres 02 01 14 14 01 01 02 01
- cUF = Código da UF do destinatário ou remetente do Documento Fiscal, informar 99 quando a
operação for de comércio exterior;
- tpEmis = Forma de Emissão da NF-e, informar 2-Contingência FS ou 5- Contingência FS-DA,
conforme o Anexo I.
- CNPJ = CNPJ do destinatário ou do remetente, informar zeros no caso de operação com o exterior
ou o CPF caso o destinatário ou remetente seja pessoa física;
- vNF = Valor Total da NF-e (sem ponto decimal, informar sempre os centavos);
- DD = Dia da emissão da NF-e;
- DV = Dígito Verificador, calculado de forma igual ao DV da Chave de Acesso (item 5.4).
Obs. Todos os campos que formam o código de barras devem ser preenchidos com
alinhamento à direita, sem formatação e com os zeros não significativos necessários para
alcançar o tamanho do campo.

         */
        String uf   = inf.getIde().getCUF();
        tipoEmissao = inf.getIde().getTpEmis();
        codigoTipoEmissao = tipoEmissao;
        String codigoBarrasString = uf+tipoEmissao+emitenteCnpj+somenteNumeros(valorTotalNota, 14);

        /**
         * ICMSp = Destaque de ICMS próprio na NF-e no seguinte formato:
         * 1 = há destaque de ICMS próprio;
         * 2 = não há destaque de ICMS próprio.
         */
        Double valor = Double.parseDouble(this.valorIcms);
        codigoBarrasString += (valor>0?"1":"2");

        /**
         * ICMSs = Destaque de ICMS por substituição tributária na NF-e, no seguinte formato:
         * 1 = há destaque de ICMS por substituição tributária;
         * 2 = não há destaque de ICMS por substituição tributária.
         */
        valor = Double.parseDouble(this.valorIcmsSubstituicao);
        codigoBarrasString += (valor>0?"1":"2");

        /**
         * Somente dia de emissao
         */
        codigoBarrasString += dataEmissao.substring(0, 2);
        codigoBarrasString += getDigitoCodigoBarras(codigoBarrasString);
        formataChaveAcesso(chaveAcesso);
        codigoBarrasString = chaveAcesso;
        try {
            Barcode barcode = net.sourceforge.barbecue.BarcodeFactory.createCode128C(codigoBarrasString);
            barcode.setDrawingText(false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BarcodeImageHandler.writePNG(barcode, baos);
            byte[] bytes = baos.toByteArray();
            ImageIcon image = new ImageIcon(bytes);
            codigoBarras = image.getImage();
        } catch (OutputException ex) {
            ex.printStackTrace();
        } catch (BarcodeException ex) {
            ex.printStackTrace();
        }
        

        List<br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det> detalhes = inf.getDet();
        for(br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det detalhe:detalhes) {
            this.itens.add(new NfeDanfeItem(detalhe));
        }
    }

    public NfeDanfe(br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc proc) {
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe nfe = proc.getNFe();
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe inf = nfe.getInfNFe();
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Ide id = inf.getIde();
        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setMinimumIntegerDigits(9);
        nf.setGroupingUsed(true);
        versao = 200;
        Long longNNfe = new Long(id.getNNF());
        numeroNfe = nf.format(longNNfe);
        serieNfe = id.getSerie();
        saidaEntrada = id.getTpNF();
        naturezaOperacao = id.getNatOp();
        String emissao = id.getDEmi();
        if (!StringUtil.isNull(emissao)) {
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat bdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                if (!StringUtil.isNull(id.getDEmi())) {
                    dataEmissao = bdf.format(sdf.parse(id.getDEmi()));
                }
                if (id.getDSaiEnt() != null) {
                    dataSaidaEntrada = bdf.format(sdf.parse(id.getDSaiEnt()));
                }
            } catch (ParseException ex) {
                System.out.println(ex.getMessage());
                System.out.println(ex);
            }
        }


//        proc.getProtocolo().getInformacoes().getNumeroProtocolo();
//        proc.getProtocolo().getInformacoes().getDataHoraRecebimento();
//        proc.getProtocolo().getInformacoes().getTipoAmbiente();GHH
//        proc.getProtocolo().getInformacoes().getDataHoraRecebimento();
        situacao = proc.getProtNFe().getInfProt().getCStat();
        if("100".equals(situacao)) {
            situacao = "AUTORIZADA";
        } else if ("101".equals(situacao) || "135".equals(situacao)) {
            situacao = "CANCELADA";
        } else if ("102".equals(situacao)) {
            situacao = "INUTILIZADA";
        }
        protocoloDataAutorizacao = proc.getProtNFe().getInfProt().getNProt();
        protocoloDataAutorizacao += " - ";
        protocoloDataAutorizacao += proc.getProtNFe().getInfProt().getDhRecbto().toString();

        msgAutenticidade = "Consulta de autenticidade no portal nacional da "
                         + "NF-e www.nfe.fazenda.gov.br/portal ou no site "
                         + "da Sefaz Autorizadora";

        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Emit emit = inf.getEmit();
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Dest dest = inf.getDest();
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Total tot = inf.getTotal();

        inscricaoMunicipal = emit.getIM();

        horaSaida = id.getHSaiEnt();
        emitenteRazaoSocial = StringUtil.htmlIso8859decode(emit.getXNome());
        emitenteEndereco = StringUtil.htmlIso8859decode(emit.getEnderEmit().getXLgr());
        if(emit.getEnderEmit().getNro()!=null && !"".equals(emit.getEnderEmit().getNro())) {
            emitenteEndereco += ", "+emit.getEnderEmit().getNro();
        }
        emitenteBairro = StringUtil.htmlIso8859decode(emit.getEnderEmit().getXBairro());
        emitenteMunicipio = emit.getEnderEmit().getXMun();
        emitenteUf = emit.getEnderEmit().getUF().toString();
        emitenteInscricaoEstadual = emit.getIE();
        emitenteInscricaoEstadualSubtributario = emit.getIEST();
        emitenteCnpj = emit.getCNPJ();
        emitenteCep = emit.getEnderEmit().getCEP();
        
        emitenteTelefone = formataTelefone(emit.getEnderEmit().getFone());
        if(emitenteTelefone!=null) {
            emitenteCep += "\n";
        }
        destinatarioRazaoSocial = StringUtil.htmlIso8859decode(dest.getXNome());
        destinatarioCnpjCpf = (dest.getCNPJ()==null || "".equals(dest.getCNPJ())?dest.getCPF():dest.getCNPJ());
        destinatarioEndereco = soPrimeiraMaiuscula(StringUtil.htmlIso8859decode(dest.getEnderDest().getXLgr()));
        if (null != dest.getEnderDest().getNro() && !"".equals(dest.getEnderDest().getNro())
                && !"0".equals(dest.getEnderDest().getNro())) {
            destinatarioEndereco += ", " + dest.getEnderDest().getNro();
        }
        if(null != dest.getEnderDest().getXCpl() && !"".equals(dest.getEnderDest().getXCpl())){
            destinatarioEndereco += ", " + soPrimeiraMaiuscula(StringUtil.htmlIso8859decode(dest.getEnderDest().getXCpl()));
        }
        destinatarioBairro = StringUtil.htmlIso8859decode(dest.getEnderDest().getXBairro());
        destinatarioCep = dest.getEnderDest().getCEP();
        destinatarioMunicipio = dest.getEnderDest().getXMun();
        destinatarioFoneFax = dest.getEnderDest().getFone();
        destinatarioUf = dest.getEnderDest().getUF().toString();
        destinatarioInscricaoEstadual = dest.getIE();
        String indPag = inf.getIde().getIndPag();
        
        if("0".equals(indPag)) {
            fatura = "pagamento à vista";
        } else if("1".equals(indPag)) {
            fatura = "À prazo";
        } else if("2".equals(indPag)) {
            fatura = "outros.";
        }
        boolean insereCobrancaXmlAtravezNfesPagamentosParcelas = Boolean.parseBoolean(System.getProperty("insere.cobrancaXml.atravez.nfesPagamentosParcelas", "false"));
        if (inf.getCobr() != null && inf.getCobr().getDup() != null) {
            List<br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Cobr.Dup> dups = inf.getCobr().getDup();
            boolean first = true;
            for (br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Cobr.Dup dup : dups) {
                String dupVencimento = dup.getDVenc().substring(8)+
                        "/"+dup.getDVenc().substring(5,7)+
                        "/"+dup.getDVenc().substring(2,4);
                if(insereCobrancaXmlAtravezNfesPagamentosParcelas) {
                    if(first) {
                        fatura += " Venc.: ";
                        first = false;
                    }
                    fatura += dupVencimento + " R$" + dup.getVDup().replace(".", ",") + " ";
                } else {
                    fatura += " Dup." + dup.getNDup() + " " + dupVencimento + " $" + dup.getVDup().replace(".", ",");
                }                
            }
        }
        baseIcms = tot.getICMSTot().getVBC();
        valorIcms = tot.getICMSTot().getVICMS();
        baseIcmsSubstituicao = tot.getICMSTot().getVBCST();
        valorIcmsSubstituicao = tot.getICMSTot().getVST();
        valorTotalProduto = tot.getICMSTot().getVProd();
        valorFrete = tot.getICMSTot().getVFrete();
        valorSeguro = tot.getICMSTot().getVSeg();
        desconto = tot.getICMSTot().getVDesc();
        outrasDespesas = tot.getICMSTot().getVOutro();
        valorIpi = tot.getICMSTot().getVIPI();
        valorTotalNota = tot.getICMSTot().getVNF();
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Transp transporte = inf.getTransp();
        if (transporte != null) {
            if (transporte.getTransporta() != null) {
                transportadorRazaoSocial = StringUtil.htmlIso8859decode(transporte.getTransporta().getXNome());
                transportadorCnpjCpf = transporte.getTransporta().getCNPJ();
                transportadorEndereco = StringUtil.htmlIso8859decode(transporte.getTransporta().getXEnder());
                transportadorMunicipio = transporte.getTransporta().getXMun();
                if (null != transporte.getTransporta().getUF()) {
                    transportadorUf = transporte.getTransporta().getUF().toString();
                }
                transportadorInscricaoEstadual = transporte.getTransporta().getIE();
            }
            transportadorFretePorConta = transporte.getModFrete();
            if (transporte.getVeicTransp() != null) {
                transportadorCodigoAntt = transporte.getVeicTransp().getRNTC();
                transportadorPlacaVeiculo = transporte.getVeicTransp().getPlaca();
                transportadorUfVeiculo = transporte.getVeicTransp().getUF().toString();

            }
        }
        
        if(transporte.getVol() != null && !transporte.getVol().isEmpty()){
            List<br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Transp.Vol> volumes = transporte.getVol();
            for(br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Transp.Vol volume:volumes){
                transportadorPesoBruto = volume.getPesoB();
                transportadorPesoLiquido = volume.getPesoL();
                transportadorEspecie = volume.getEsp();
                transportadorMarca = volume.getMarca();
                transportadorQuantidade = volume.getQVol();
                transportadorNumeracao = volume.getNVol();
            }
        }

        if (tot != null) {
            br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Total.ISSQNtot issqn = tot.getISSQNtot();
            if (issqn != null) {
                valorTotalServicos = issqn.getVServ();
                valorPis    = issqn.getVPIS();
                valorCofins = issqn.getVCOFINS();
                baseIssqn   = issqn.getVBC();
                valorIssqn  = issqn.getVISS();
            }
        }
        tipoEmissao = id.getTpEmis();
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.InfAdic infAd = inf.getInfAdic();
        if (infAd != null) {
            informacoesComplementares = infAd.getInfCpl();
            if(informacoesComplementares==null) {
                informacoesComplementares = "";
            }
                
            informacoesComplementares2 = infAd.getInfAdFisco();
            if (informacoesComplementares2 != null) {
                if(!"".equals(informacoesComplementares)){
                    informacoesComplementares += "\n";
                }
                informacoesComplementares += "Informações Adicionais de Interesse do Fisco: ";
                informacoesComplementares += informacoesComplementares2;
            }
        }

        chaveAcesso = inf.getId().replace("NFe", "");
        StringBuilder sb = new StringBuilder();
        int limit = 10;
        for(int i=0;i<=limit;i++) {
            int j = i*4;
            sb.append(chaveAcesso.substring(j, j+4));
            if(i<limit) {
                sb.append(" ");
            }
        }

        chaveAcesso = sb.toString();
        /**
         *
         * O Código de Barras Adicional dos Dados da NF-e será formado pelo seguinte conteúdo, em
um total de 36 caracteres:
cUF tpEmis CNPJ vNF ICMSp ICMSs DD DV
Quantidade de caracteres 02 01 14 14 01 01 02 01
- cUF = Código da UF do destinatário ou remetente do Documento Fiscal, informar 99 quando a
operação for de comércio exterior;
- tpEmis = Forma de Emissão da NF-e, informar 2-Contingência FS ou 5- Contingência FS-DA,
conforme o Anexo I.
- CNPJ = CNPJ do destinatário ou do remetente, informar zeros no caso de operação com o exterior
ou o CPF caso o destinatário ou remetente seja pessoa física;
- vNF = Valor Total da NF-e (sem ponto decimal, informar sempre os centavos);
- DD = Dia da emissão da NF-e;
- DV = Dígito Verificador, calculado de forma igual ao DV da Chave de Acesso (item 5.4).
Obs. Todos os campos que formam o código de barras devem ser preenchidos com
alinhamento à direita, sem formatação e com os zeros não significativos necessários para
alcançar o tamanho do campo.

         */
        String uf   = inf.getIde().getCUF();
        tipoEmissao = inf.getIde().getTpEmis();
        codigoTipoEmissao = tipoEmissao;
        String codigoBarrasString = uf+tipoEmissao+emitenteCnpj+somenteNumeros(valorTotalNota, 14);

        /**
         * ICMSp = Destaque de ICMS próprio na NF-e no seguinte formato:
         * 1 = há destaque de ICMS próprio;
         * 2 = não há destaque de ICMS próprio.
         */
        Double valor = Double.parseDouble(this.valorIcms);
        codigoBarrasString += (valor>0?"1":"2");

        /**
         * ICMSs = Destaque de ICMS por substituição tributária na NF-e, no seguinte formato:
         * 1 = há destaque de ICMS por substituição tributária;
         * 2 = não há destaque de ICMS por substituição tributária.
         */
        valor = Double.parseDouble(this.valorIcmsSubstituicao);
        codigoBarrasString += (valor>0?"1":"2");

        /**
         * Somente dia de emissao
         */
        if (versao == 200) {
            codigoBarrasString += dataEmissao.substring(0, 2);
        } else if (versao == 310) {
            codigoBarrasString += dataEmissao.substring(0, 2);
        } else {
            codigoBarrasString += "00";
        }
        codigoBarrasString += getDigitoCodigoBarras(codigoBarrasString);
        formataChaveAcesso(chaveAcesso);
        codigoBarrasString = chaveAcesso;
        try {
            Barcode barcode = net.sourceforge.barbecue.BarcodeFactory.createCode128C(codigoBarrasString);
            barcode.setDrawingText(false);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BarcodeImageHandler.writePNG(barcode, baos);
            byte[] bytes = baos.toByteArray();
            ImageIcon image = new ImageIcon(bytes);
            codigoBarras = image.getImage();
        } catch (OutputException ex) {
            ex.printStackTrace();
        } catch (BarcodeException ex) {
            ex.printStackTrace();
        }
        

        List<br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det> detalhes = inf.getDet();
        for(br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det detalhe:detalhes) {
            this.itens.add(new NfeDanfeItem(detalhe));
        }
    }
    
    

    public String getBaseIcms() {
        return baseIcms;
    }

    public void setBaseIcms(String baseIcms) {
        this.baseIcms = baseIcms;
    }

    public String getBaseIcmsSubstituicao() {
        return baseIcmsSubstituicao;
    }

    public void setBaseIcmsSubstituicao(String baseIcmsSubstituicao) {
        this.baseIcmsSubstituicao = baseIcmsSubstituicao;
    }

    public String getBaseIssqn() {
        return baseIssqn;
    }

    public void setBaseIssqn(String baseIssqn) {
        this.baseIssqn = baseIssqn;
    }

    public String getChaveAcesso() {
        return chaveAcesso;
    }

    public void setChaveAcesso(String chaveAcesso) {
        this.chaveAcesso = chaveAcesso;
    }

    public Image getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(Image codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Image getCodigoBarrasContingencia() {
        return codigoBarrasContingencia;
    }

    public void setCodigoBarrasContingencia(Image codigoBarrasContingencia) {
        this.codigoBarrasContingencia = codigoBarrasContingencia;
    }

    public String getCodigoTipoEmissao() {
        return codigoTipoEmissao;
    }

    public void setCodigoTipoEmissao(String codigoTipoEmissao) {
        this.codigoTipoEmissao = codigoTipoEmissao;
    }

    public String getDadosNfe() {
        return dadosNfe;
    }

    public void setDadosNfe(String dadosNfe) {
        this.dadosNfe = dadosNfe;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDataRegistroDPEC() {
        return dataRegistroDPEC;
    }

    public void setDataRegistroDPEC(String dataRegistroDPEC) {
        this.dataRegistroDPEC = dataRegistroDPEC;
    }

    public String getDataSaidaEntrada() {
        return dataSaidaEntrada;
    }

    public void setDataSaidaEntrada(String dataSaidaEntrada) {
        this.dataSaidaEntrada = dataSaidaEntrada;
    }

    public String getDesconto() {
        return desconto;
    }

    public void setDesconto(String desconto) {
        this.desconto = desconto;
    }

    public String getDestinatarioBairro() {
        return destinatarioBairro;
    }

    public void setDestinatarioBairro(String destinatarioBairro) {
        this.destinatarioBairro = destinatarioBairro;
    }

    public String getDestinatarioCep() {
        return formataCep(destinatarioCep);
    }

    public void setDestinatarioCep(String destinatarioCep) {
        this.destinatarioCep = destinatarioCep;
    }

    public String getDestinatarioCnpjCpf() {
        return formataCpfCnpj(destinatarioCnpjCpf);
    }

    public void setDestinatarioCnpjCpf(String destinatarioCnpjCpf) {
        this.destinatarioCnpjCpf = destinatarioCnpjCpf;
    }

    public String getDestinatarioEndereco() {
        return destinatarioEndereco;
    }

    public void setDestinatarioEndereco(String destinatarioEndereco) {
        this.destinatarioEndereco = destinatarioEndereco;
    }

    public String getDestinatarioFoneFax() {
        return destinatarioFoneFax;
    }

    public void setDestinatarioFoneFax(String destinatarioFoneFax) {
        this.destinatarioFoneFax = destinatarioFoneFax;
    }

    public String getDestinatarioInscricaoEstadual() {
        return destinatarioInscricaoEstadual;
    }

    public void setDestinatarioInscricaoEstadual(String destinatarioInscricaoEstadual) {
        this.destinatarioInscricaoEstadual = destinatarioInscricaoEstadual;
    }

    public String getDestinatarioMunicipio() {
        return destinatarioMunicipio;
    }

    public void setDestinatarioMunicipio(String destinatarioMunicipio) {
        this.destinatarioMunicipio = destinatarioMunicipio;
    }

    public String getDestinatarioRazaoSocial() {
        return destinatarioRazaoSocial;
    }

    public void setDestinatarioRazaoSocial(String destinatarioRazaoSocial) {
        this.destinatarioRazaoSocial = destinatarioRazaoSocial;
    }

    public String getDestinatarioUf() {
        return destinatarioUf;
    }

    public void setDestinatarioUf(String destinatarioUf) {
        this.destinatarioUf = destinatarioUf;
    }

    public String getEmitenteBairro() {
        return emitenteBairro;
    }

    public void setEmitenteBairro(String emitenteBairro) {
        this.emitenteBairro = emitenteBairro;
    }

    public String getEmitenteCep() {
        return formataCep(emitenteCep);
    }

    public void setEmitenteCep(String emitenteCep) {
        this.emitenteCep = emitenteCep;
    }

    public String getEmitenteCnpj() {
        return formataCpfCnpj(emitenteCnpj);
    }

    public void setEmitenteCnpj(String emitenteCnpj) {
        this.emitenteCnpj = emitenteCnpj;
    }

    public String getEmitenteEndereco() {
        return emitenteEndereco;
    }

    public void setEmitenteEndereco(String emitenteEndereco) {
        this.emitenteEndereco = emitenteEndereco;
    }

    public String getEmitenteInscricaoEstadual() {
        return emitenteInscricaoEstadual;
    }

    public void setEmitenteInscricaoEstadual(String emitenteInscricaoEstadual) {
        this.emitenteInscricaoEstadual = emitenteInscricaoEstadual;
    }

    public String getEmitenteInscricaoEstadualSubtributario() {
        return emitenteInscricaoEstadualSubtributario;
    }

    public void setEmitenteInscricaoEstadualSubtributario(String emitenteInscricaoEstadualSubtributario) {
        this.emitenteInscricaoEstadualSubtributario = emitenteInscricaoEstadualSubtributario;
    }

    public Image getEmitenteLogoTipo() {
        return emitenteLogoTipo;
    }

    public void setEmitenteLogoTipo(Image emitenteLogoTipo) {
        this.emitenteLogoTipo = emitenteLogoTipo;
    }

    public String getEmitenteMunicipio() {
        return emitenteMunicipio;
    }

    public void setEmitenteMunicipio(String emitenteMunicipio) {
        this.emitenteMunicipio = emitenteMunicipio;
    }

    public String getEmitenteRazaoSocial() {
        return emitenteRazaoSocial;
    }

    public void setEmitenteRazaoSocial(String emitenteRazaoSocial) {
        this.emitenteRazaoSocial = emitenteRazaoSocial;
    }

    public String getEmitenteTelefone() {
        return emitenteTelefone;
    }

    public void setEmitenteTelefone(String emitenteTelefone) {
        this.emitenteTelefone = emitenteTelefone;
    }

    public String getEmitenteUf() {
        return emitenteUf;
    }

    public void setEmitenteUf(String emitenteUf) {
        this.emitenteUf = emitenteUf;
    }

    public String getFatura() {
        return fatura;
    }

    public void setFatura(String fatura) {
        this.fatura = fatura;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getInformacoesComplementares() {
        return informacoesComplementares;
    }

    public void setInformacoesComplementares(String informacoesComplementares) {
        this.informacoesComplementares = informacoesComplementares;
    }

    public String getInformacoesComplementares2() {
        return informacoesComplementares2;
    }

    public void setInformacoesComplementares2(String informacoesComplementares2) {
        this.informacoesComplementares2 = informacoesComplementares2;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getMsgAutenticidade() {
        return msgAutenticidade;
    }

    public void setMsgAutenticidade(String msgAutenticidade) {
        this.msgAutenticidade = msgAutenticidade;
    }

    public String getNaturezaOperacao() {
        return naturezaOperacao;
    }

    public void setNaturezaOperacao(String naturezaOperacao) {
        this.naturezaOperacao = naturezaOperacao;
    }

    public String getNumeroNfe() {
        return numeroNfe;
    }

    public void setNumeroNfe(String numeroNfe) {
        this.numeroNfe = numeroNfe;
    }

    public String getNumeroRegistroDPEC() {
        return numeroRegistroDPEC;
    }

    public void setNumeroRegistroDPEC(String numeroRegistroDPEC) {
        this.numeroRegistroDPEC = numeroRegistroDPEC;
    }

    public String getOutrasDespesas() {
        return outrasDespesas;
    }

    public void setOutrasDespesas(String outrasDespesas) {
        this.outrasDespesas = outrasDespesas;
    }

    public String getProtocoloDataAutorizacao() {
        return protocoloDataAutorizacao;
    }

    public void setProtocoloDataAutorizacao(String protocoloDataAutorizacao) {
        this.protocoloDataAutorizacao = protocoloDataAutorizacao;
    }

    public String getSaidaEntrada() {
        return saidaEntrada;
    }

    public void setSaidaEntrada(String saidaEntrada) {
        this.saidaEntrada = saidaEntrada;
    }

    public String getSerieNfe() {
        return serieNfe;
    }

    public void setSerieNfe(String serieNfe) {
        this.serieNfe = serieNfe;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTipoEmissao() {
        return tipoEmissao;
    }

    public void setTipoEmissao(String tipoEmissao) {
        this.tipoEmissao = tipoEmissao;
    }

    public String getTransportadorCnpjCpf() {
        return formataCpfCnpj(transportadorCnpjCpf);
    }

    public void setTransportadorCnpjCpf(String transportadorCnpjCpf) {
        this.transportadorCnpjCpf = transportadorCnpjCpf;
    }

    public String getTransportadorCodigoAntt() {
        return transportadorCodigoAntt;
    }

    public void setTransportadorCodigoAntt(String transportadorCodigoAntt) {
        this.transportadorCodigoAntt = transportadorCodigoAntt;
    }

    public String getTransportadorEndereco() {
        return transportadorEndereco;
    }

    public void setTransportadorEndereco(String transportadorEndereco) {
        this.transportadorEndereco = transportadorEndereco;
    }

    public String getTransportadorEspecie() {
        return transportadorEspecie;
    }

    public void setTransportadorEspecie(String transportadorEspecie) {
        this.transportadorEspecie = transportadorEspecie;
    }

    public String getTransportadorFretePorConta() {
        return transportadorFretePorConta;
    }

    public void setTransportadorFretePorConta(String transportadorFretePorConta) {
        this.transportadorFretePorConta = transportadorFretePorConta;
    }

    public String getTransportadorInscricaoEstadual() {
        return transportadorInscricaoEstadual;
    }

    public void setTransportadorInscricaoEstadual(String transportadorInscricaoEstadual) {
        this.transportadorInscricaoEstadual = transportadorInscricaoEstadual;
    }

    public String getTransportadorMarca() {
        return transportadorMarca;
    }

    public void setTransportadorMarca(String transportadorMarca) {
        this.transportadorMarca = transportadorMarca;
    }

    public String getTransportadorMunicipio() {
        return transportadorMunicipio;
    }

    public void setTransportadorMunicipio(String transportadorMunicipio) {
        this.transportadorMunicipio = transportadorMunicipio;
    }

    public String getTransportadorNumeracao() {
        return transportadorNumeracao;
    }

    public void setTransportadorNumeracao(String transportadorNumeracao) {
        this.transportadorNumeracao = transportadorNumeracao;
    }

    public String getTransportadorPesoBruto() {
        return transportadorPesoBruto;
    }

    public void setTransportadorPesoBruto(String transportadorPesoBruto) {
        this.transportadorPesoBruto = transportadorPesoBruto;
    }

    public String getTransportadorPesoLiquido() {
        return transportadorPesoLiquido;
    }

    public void setTransportadorPesoLiquido(String transportadorPesoLiquido) {
        this.transportadorPesoLiquido = transportadorPesoLiquido;
    }

    public String getTransportadorPlacaVeiculo() {
        return transportadorPlacaVeiculo;
    }

    public void setTransportadorPlacaVeiculo(String transportadorPlacaVeiculo) {
        this.transportadorPlacaVeiculo = transportadorPlacaVeiculo;
    }

    public String getTransportadorQuantidade() {
        return transportadorQuantidade;
    }

    public void setTransportadorQuantidade(String transportadorQuantidade) {
        this.transportadorQuantidade = transportadorQuantidade;
    }

    public String getTransportadorRazaoSocial() {
        return transportadorRazaoSocial;
    }

    public void setTransportadorRazaoSocial(String transportadorRazaoSocial) {
        this.transportadorRazaoSocial = transportadorRazaoSocial;
    }

    public String getTransportadorUf() {
        return transportadorUf;
    }

    public void setTransportadorUf(String transportadorUf) {
        this.transportadorUf = transportadorUf;
    }

    public String getTransportadorUfVeiculo() {
        return transportadorUfVeiculo;
    }

    public void setTransportadorUfVeiculo(String transportadorUfVeiculo) {
        this.transportadorUfVeiculo = transportadorUfVeiculo;
    }

    public String getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(String valorFrete) {
        this.valorFrete = valorFrete;
    }

    public String getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(String valorIcms) {
        this.valorIcms = valorIcms;
    }

    public String getValorIcmsSubstituicao() {
        return valorIcmsSubstituicao;
    }

    public void setValorIcmsSubstituicao(String valorIcmsSubstituicao) {
        this.valorIcmsSubstituicao = valorIcmsSubstituicao;
    }

    public String getValorIpi() {
        return valorIpi;
    }

    public void setValorIpi(String valorIpi) {
        this.valorIpi = valorIpi;
    }

    public String getValorIssqn() {
        return valorIssqn;
    }

    public void setValorIssqn(String valorIssqn) {
        this.valorIssqn = valorIssqn;
    }

    public String getValorCofins() {
        return valorCofins;
    }

    public void setValorCofins(String valorCofins) {
        this.valorCofins = valorCofins;
    }

    public String getValorPis() {
        return valorPis;
    }

    public void setValorPis(String valorPis) {
        this.valorPis = valorPis;
    }

    public String getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(String valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public String getValorTotalNota() {
        return valorTotalNota;
    }

    public void setValorTotalNota(String valorTotalNota) {
        this.valorTotalNota = valorTotalNota;
    }

    public String getValorTotalProduto() {
        return valorTotalProduto;
    }

    public void setValorTotalProduto(String valorTotalProduto) {
        this.valorTotalProduto = valorTotalProduto;
    }

    public String getValorTotalServicos() {
        return valorTotalServicos;
    }

    public void setValorTotalServicos(String valorTotalServicos) {
        this.valorTotalServicos = valorTotalServicos;
    }

    public List<NfeDanfeItem> getItens() {
        return itens;
    }

    public void setItens(List<NfeDanfeItem> itens) {
        this.itens = itens;
    }

    private String somenteNumeros(String valorTotalNota, int i) {
        char[] array = valorTotalNota.toCharArray();
        StringBuilder sb = new StringBuilder();

        for(char c:array) {
            if(Character.isDigit(c)) {
                sb.append(c);
            }
        }
        for(int j=sb.length();j<i;j++) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    private String getDigitoCodigoBarras(String campo) {
        //Esta rotina faz o calcula no modulo 11 - 23456789

        int multiplicador = 4;
        int multiplicacao = 0;
        int soma_campo = 0;

        for (int i = 0; i < campo.length(); i++) {
            multiplicacao = Integer.parseInt(campo.substring(i,1+i)) * multiplicador;
            soma_campo = soma_campo + multiplicacao;
            multiplicador = ((multiplicador+5) % 8) + 2;
        }

        int dac = 11 - (soma_campo%11);
        if (dac == 0 || dac == 1 || dac > 9)
            dac = 1;

        campo = String.valueOf(dac);
        return campo;
    }

    private String formataCpfCnpj(String cpfCnpj) {
        if(cpfCnpj==null) {
            return cpfCnpj;
        }
        if(cpfCnpj.length()==14) {
            return formataCnpj(cpfCnpj);
        } else if(cpfCnpj.length()==11) {
            return formataCpf(cpfCnpj);
        } else {
            return cpfCnpj;
        }

    }

    private String formataCnpj(String cnpj) {
        if(cnpj!=null && cnpj.length()==14) {
            return cnpj.substring(0, 2)+"."+cnpj.substring(2, 5)+"."+
                   cnpj.substring(5, 8)+"/"+cnpj.substring(8, 12)+"-"+
                   cnpj.substring(12);
        }
        return cnpj;
        
    }

    private String formataCpf(String cpf) {
        if(cpf!=null && cpf.length()==11) {
            return cpf.substring(0, 3)+"."+cpf.substring(3, 6)+"."+
                   cpf.substring(6, 9)+"-"+cpf.substring(9);
        }
        return cpf;
        
    }

    private String formataCep(String cep) {
        if (cep != null && cep.length() == 8) {
            return cep.substring(0, 5) + "-" + cep.substring(5);
        }
        return cep;
    }

    private String formataTelefone(String fone) {
        if (fone != null) {
            if (fone.length() == 10) {
                return "(" + fone.substring(0, 2)
                        + ")" + fone.substring(2, 6)
                        + "-" + fone.substring(6);
            } else if (fone.length() == 11) {
                return "(" + fone.substring(0, 3)
                        + ")" + fone.substring(3, 7)
                        + "-" + fone.substring(7);
            }
        }
        
        return fone;
    }

    private void formataChaveAcesso(String chaveAcessoComEspaco) {
        String chaveAcessoSemEspacos = "";
        int inicio = 0;
        int fim = 4;
        if (chaveAcessoComEspaco.length() > 44) {
            if (chaveAcessoComEspaco.length() == 54) {
                //<=11 pois a chave de acesso esta com 54 digitos, 44 numeros e 10 espaços em branco
                //entao ela fica separada em 11 pedaços de 4 numeros
                for (int i = 1; i <= 11; i++) {
                    chaveAcessoSemEspacos += chaveAcessoComEspaco.substring(inicio, fim).trim();
                    inicio += 5;
                    fim += 5;
                }
                chaveAcesso = chaveAcessoSemEspacos;
            }
        }
    }
    
    private static String soPrimeiraMaiuscula(String astring) {
        if (astring == null) {
            return null;
        }
        String palavras[] = astring.split(" ");
        StringBuilder sb = new StringBuilder();
        boolean primeira = true;
        for (String palavra : palavras) {
            if (primeira) {
                primeira = false;
            } else {
                sb.append(" ");
            }
            String tratada = soPrimeiraMaiusculaPalavra(palavra);
            sb.append(tratada);
        }
        return sb.toString();
    }
    
    private static String soPrimeiraMaiusculaPalavra(String astring) {
        if (astring == null) {
            return null;
        }
        String string = astring.toLowerCase();
        if (string.length() == 1) {
            return string;
        }
        if (string.equals("da") || string.equals("de") || string.equals("do")) {
            return string;
        }
        return (string.length() > 2 ? string.substring(0, 1).toUpperCase() + string.substring(1) : string.toUpperCase());
    }
}
