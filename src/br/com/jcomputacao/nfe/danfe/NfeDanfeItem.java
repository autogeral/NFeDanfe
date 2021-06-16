package br.com.jcomputacao.nfe.danfe;

import br.com.jcomputacao.nfe.danfe.util.StringUtil;
import java.util.List;
import javax.xml.bind.JAXBElement;

/**
 * Auterando em 18/02/2015 para suportar o novo schema da NFe
 * 11/07/2010 16:56:29
 * @author Murilo
 */
public class NfeDanfeItem {
    private String codigo;
    private String descricao;
    private String baseIcms;
    private String valorIcms;
    private String valorIpi;
    private String ncm;
    private String cst;
    private String cfop;
    private String valorTotal;
    private String aliquotaIcms;
    private String aliquotaIpi;
    private String unidadeTributaria;
    private String valorUnitarioComercial;
    private String quantidadeComercial;
    private String valorUnitarioTributario;
    private String quantidadeTributaria;
    private String unidadeComercial;
    private String infCompl = "";

    public NfeDanfeItem() {

    }

    public NfeDanfeItem(br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det detalhe) {
        if (detalhe.getProd() != null) {
            this.codigo = detalhe.getProd().getCProd();
            this.descricao = StringUtil.htmlIso8859decode(detalhe.getProd().getXProd());
            this.ncm = detalhe.getProd().getNCM();
            this.cfop = detalhe.getProd().getCFOP();
            this.valorUnitarioComercial = detalhe.getProd().getVUnCom();
            this.unidadeComercial = detalhe.getProd().getUCom();
            this.quantidadeComercial = detalhe.getProd().getQCom();
            this.quantidadeTributaria = detalhe.getProd().getQTrib();
            this.valorTotal = detalhe.getProd().getVProd();
            this.unidadeTributaria = detalhe.getProd().getUTrib();
            this.valorUnitarioTributario = detalhe.getProd().getVUnTrib();
            //this.codigoB = detalhe.getProd().getCodigBarras();
        }
        br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det.Imposto imp = detalhe.getImposto();
        if (imp != null) {
            List<JAXBElement<?>> content = imp.getContent();
            for (int i = 0; i < content.size(); i++) {
                JAXBElement<?> j = content.get(i);

                if (j.getValue() instanceof br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TIpi) {
                    br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TIpi ipi = (br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TIpi) j.getValue();
                    if (ipi != null
                            && ipi.getIPITrib() != null) {
                        this.aliquotaIpi = ipi.getIPITrib().getPIPI();
                        this.valorIpi = ipi.getIPITrib().getVIPI();
                    }
                }

                if (j.getValue() instanceof br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det.Imposto.ICMS) {
                    br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det.Imposto.ICMS icms = 
                            (br.inf.portalfiscal.nfe.xml.pl008h2.nfes.TNFe.InfNFe.Det.Imposto.ICMS) j.getValue();
                    if (icms.getICMS00() != null) {
                        this.aliquotaIcms = icms.getICMS00().getPICMS();
                        this.baseIcms = icms.getICMS00().getVBC();
                        this.valorIcms = icms.getICMS00().getVICMS();
                        this.cst = icms.getICMS00().getOrig() + icms.getICMS00().getCST();
                    } else if (icms.getICMS10() != null) {
                        this.aliquotaIcms = icms.getICMS10().getPICMS();
                        this.baseIcms = icms.getICMS10().getVBC();
                        this.valorIcms = icms.getICMS10().getVICMS();
                        this.cst = icms.getICMS10().getOrig() + icms.getICMS10().getCST();
                    } else if (icms.getICMS20() != null) {
                        this.aliquotaIcms = icms.getICMS20().getPICMS();
                        this.baseIcms = icms.getICMS20().getVBC();
                        this.valorIcms = icms.getICMS20().getVICMS();
                        this.cst = icms.getICMS20().getOrig() + icms.getICMS20().getCST();
                    } else if (icms.getICMS30() != null) {
                        this.cst = icms.getICMS30().getOrig() + icms.getICMS30().getCST();
//                this.aliquotaIcms = imp.getICMS().getICMS30().getAliquotaImposto();
//                this.baseIcms = imp.getICMS().getICMS30().getValorBCdoICMS();
//                this.valorIcms = imp.getICMS().getICMS30().getValorICMS();
                    } else if (icms.getICMS40() != null) {
                        this.cst = icms.getICMS40().getOrig() + icms.getICMS40().getCST();
//                this.aliquotaIcms = imp.getICMS().getICMS40().getAliquotaImposto();
//                this.baseIcms = imp.getICMS().getICMS40().getValorBCdoICMS();
//                this.valorIcms = imp.getICMS().getICMS40().getValorICMS();
                    } else if (icms.getICMS51() != null) {
                        this.aliquotaIcms = icms.getICMS51().getPICMS();
                        this.baseIcms = icms.getICMS51().getVBC();
                        this.valorIcms = icms.getICMS51().getVICMS();
                        this.cst = icms.getICMS51().getOrig() + icms.getICMS51().getCST();
                    } else if (icms.getICMS60() != null) {
                        this.cst = icms.getICMS60().getOrig() + icms.getICMS60().getCST();
                    } else if (icms.getICMS70() != null) {
                        this.cst = icms.getICMS70().getOrig() + icms.getICMS70().getCST();
                        this.baseIcms = icms.getICMS70().getVBC();
                        this.aliquotaIcms = icms.getICMS70().getPICMS();
                        this.valorIcms = icms.getICMS70().getVICMS();
                    } else if (icms.getICMS90() != null) {
                        this.cst = icms.getICMS90().getOrig() + icms.getICMS90().getCST();
                    } else if (icms.getICMSSN101() != null) {
                        this.cst = icms.getICMSSN101().getOrig() + icms.getICMSSN101().getCSOSN();
                    } else if (icms.getICMSSN102() != null) {
                        this.cst = icms.getICMSSN102().getOrig() + icms.getICMSSN102().getCSOSN();
                    } else if (icms.getICMSSN201() != null) {
                        this.cst = icms.getICMSSN201().getOrig() + icms.getICMSSN201().getCSOSN();
                    } else if (icms.getICMSSN202() != null) {
                        this.cst = icms.getICMSSN202().getOrig() + icms.getICMSSN202().getCSOSN();
                    } else if (icms.getICMSSN500() != null) {
                        this.cst = icms.getICMSSN500().getOrig() + icms.getICMSSN500().getCSOSN();
                    } else if (icms.getICMSSN900().getOrig() != null) {
                        this.aliquotaIcms = icms.getICMSSN900().getPICMS();
                        this.baseIcms = icms.getICMSSN900().getVBC();
                        this.valorIcms = icms.getICMSSN900().getVICMS();
                        this.cst = icms.getICMSSN900().getOrig() + icms.getICMSSN900().getCSOSN();
                    }
                }
            }
        }
        this.infCompl = detalhe.getInfAdProd();
    }

    public NfeDanfeItem(br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TNFe.InfNFe.Det detalhe) {
        if (detalhe.getProd() != null) {
            this.codigo = detalhe.getProd().getCProd();
            this.descricao = StringUtil.htmlIso8859decode(detalhe.getProd().getXProd());
            this.ncm = detalhe.getProd().getNCM();
            this.cfop = detalhe.getProd().getCFOP();
            this.valorUnitarioComercial = detalhe.getProd().getVUnCom();
            this.unidadeComercial = detalhe.getProd().getUCom();
            this.quantidadeComercial = detalhe.getProd().getQCom();
            this.quantidadeTributaria = detalhe.getProd().getQTrib();
            this.valorTotal = detalhe.getProd().getVProd();
            this.unidadeTributaria = detalhe.getProd().getUTrib();
            this.valorUnitarioTributario = detalhe.getProd().getVUnTrib();
            //this.codigoB = detalhe.getProd().getCodigBarras();
        }
        br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TNFe.InfNFe.Det.Imposto imp = detalhe.getImposto();
        if (imp != null) {
            List<JAXBElement<?>> content = imp.getContent();
            for (int i = 0; i < content.size(); i++) {
                JAXBElement<?> j = content.get(i);

                if (j.getValue() instanceof br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TIpi) {
                    br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TIpi ipi = (br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TIpi) j.getValue();
                    if (ipi != null
                            && ipi.getIPITrib() != null) {
                        this.aliquotaIpi = ipi.getIPITrib().getPIPI();
                        this.valorIpi = ipi.getIPITrib().getVIPI();
                    }
                }

                if (j.getValue() instanceof br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TNFe.InfNFe.Det.Imposto.ICMS) {
                    br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TNFe.InfNFe.Det.Imposto.ICMS icms = 
                            (br.inf.portalfiscal.nfe.xml.pl009v4_2021.nfes.TNFe.InfNFe.Det.Imposto.ICMS) j.getValue();
                    if (icms.getICMS00() != null) {
                        this.aliquotaIcms = icms.getICMS00().getPICMS();
                        this.baseIcms = icms.getICMS00().getVBC();
                        this.valorIcms = icms.getICMS00().getVICMS();
                        this.cst = icms.getICMS00().getOrig() + icms.getICMS00().getCST();
                    } else if (icms.getICMS10() != null) {
                        this.aliquotaIcms = icms.getICMS10().getPICMS();
                        this.baseIcms = icms.getICMS10().getVBC();
                        this.valorIcms = icms.getICMS10().getVICMS();
                        this.cst = icms.getICMS10().getOrig() + icms.getICMS10().getCST();
                    } else if (icms.getICMS20() != null) {
                        this.aliquotaIcms = icms.getICMS20().getPICMS();
                        this.baseIcms = icms.getICMS20().getVBC();
                        this.valorIcms = icms.getICMS20().getVICMS();
                        this.cst = icms.getICMS20().getOrig() + icms.getICMS20().getCST();
                    } else if (icms.getICMS30() != null) {
                        this.cst = icms.getICMS30().getOrig() + icms.getICMS30().getCST();
//                this.aliquotaIcms = imp.getICMS().getICMS30().getAliquotaImposto();
//                this.baseIcms = imp.getICMS().getICMS30().getValorBCdoICMS();
//                this.valorIcms = imp.getICMS().getICMS30().getValorICMS();
                    } else if (icms.getICMS40() != null) {
                        this.cst = icms.getICMS40().getOrig() + icms.getICMS40().getCST();
//                this.aliquotaIcms = imp.getICMS().getICMS40().getAliquotaImposto();
//                this.baseIcms = imp.getICMS().getICMS40().getValorBCdoICMS();
//                this.valorIcms = imp.getICMS().getICMS40().getValorICMS();
                    } else if (icms.getICMS51() != null) {
                        this.aliquotaIcms = icms.getICMS51().getPICMS();
                        this.baseIcms = icms.getICMS51().getVBC();
                        this.valorIcms = icms.getICMS51().getVICMS();
                        this.cst = icms.getICMS51().getOrig() + icms.getICMS51().getCST();
                    } else if (icms.getICMS60() != null) {
                        this.cst = icms.getICMS60().getOrig() + icms.getICMS60().getCST();
                    } else if (icms.getICMS70() != null) {
                        this.cst = icms.getICMS70().getOrig() + icms.getICMS70().getCST();
                        this.baseIcms = icms.getICMS70().getVBC();
                        this.aliquotaIcms = icms.getICMS70().getPICMS();
                        this.valorIcms = icms.getICMS70().getVICMS();
                    } else if (icms.getICMS90() != null) {
                        this.cst = icms.getICMS90().getOrig() + icms.getICMS90().getCST();
                    } else if (icms.getICMSSN101() != null) {
                        this.cst = icms.getICMSSN101().getOrig() + icms.getICMSSN101().getCSOSN();
                    } else if (icms.getICMSSN102() != null) {
                        this.cst = icms.getICMSSN102().getOrig() + icms.getICMSSN102().getCSOSN();
                    } else if (icms.getICMSSN201() != null) {
                        this.cst = icms.getICMSSN201().getOrig() + icms.getICMSSN201().getCSOSN();
                    } else if (icms.getICMSSN202() != null) {
                        this.cst = icms.getICMSSN202().getOrig() + icms.getICMSSN202().getCSOSN();
                    } else if (icms.getICMSSN500() != null) {
                        this.cst = icms.getICMSSN500().getOrig() + icms.getICMSSN500().getCSOSN();
                    } else if (icms.getICMSSN900() != null) {
                        this.aliquotaIcms = icms.getICMSSN900().getPICMS();
                        this.baseIcms = icms.getICMSSN900().getVBC();
                        this.valorIcms = icms.getICMSSN900().getVICMS();
                        this.cst = icms.getICMSSN900().getOrig() + icms.getICMSSN900().getCSOSN();
                    }
                }
            }
        }
        this.infCompl = detalhe.getInfAdProd();
    }
    
    public NfeDanfeItem(br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det detalhe) {
        if (detalhe.getProd() != null) {
            this.codigo    = detalhe.getProd().getCProd();
            this.descricao = StringUtil.htmlIso8859decode(detalhe.getProd().getXProd());
            this.ncm       = detalhe.getProd().getNCM();
            this.cfop      = detalhe.getProd().getCFOP();
            this.valorUnitarioComercial  = detalhe.getProd().getVUnCom();
            this.unidadeComercial        = detalhe.getProd().getUCom();
            this.quantidadeComercial     = detalhe.getProd().getQCom();
            this.quantidadeTributaria    = detalhe.getProd().getQTrib();
            this.valorTotal              = detalhe.getProd().getVProd();
            this.unidadeTributaria       = detalhe.getProd().getUTrib();
            this.valorUnitarioTributario = detalhe.getProd().getVUnTrib();
            //this.codigoB = detalhe.getProd().getCodigBarras();
        }
        
        br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det.Imposto imp = detalhe.getImposto();
        if (imp != null) {
            if(imp.getIPI() != null &&
                    imp.getIPI().getIPITrib()!=null) {
                this.aliquotaIpi = imp.getIPI().getIPITrib().getPIPI();
                this.valorIpi = imp.getIPI().getIPITrib().getVIPI();
            }

            if (imp.getICMS().getICMS00() != null) {
                this.aliquotaIcms = imp.getICMS().getICMS00().getPICMS();
                this.baseIcms     = imp.getICMS().getICMS00().getVBC();
                this.valorIcms    = imp.getICMS().getICMS00().getVICMS();
                this.cst          = imp.getICMS().getICMS00().getOrig() + imp.getICMS().getICMS00().getCST();
            } else if (imp.getICMS().getICMS10() != null) {
                this.aliquotaIcms = imp.getICMS().getICMS10().getPICMS();
                this.baseIcms     = imp.getICMS().getICMS10().getVBC();
                this.valorIcms    = imp.getICMS().getICMS10().getVICMS();
                this.cst          = imp.getICMS().getICMS10().getOrig() + imp.getICMS().getICMS10().getCST();
            } else if (imp.getICMS().getICMS20() != null) {
                this.aliquotaIcms = imp.getICMS().getICMS20().getPICMS();
                this.baseIcms     = imp.getICMS().getICMS20().getVBC();
                this.valorIcms    = imp.getICMS().getICMS20().getVICMS();
                this.cst          = imp.getICMS().getICMS20().getOrig() + imp.getICMS().getICMS20().getCST();
            } else if (imp.getICMS().getICMS30() != null) {
                this.cst = imp.getICMS().getICMS30().getOrig() + imp.getICMS().getICMS30().getCST();
            } else if (imp.getICMS().getICMS40() != null) {
                this.cst = imp.getICMS().getICMS40().getOrig() + imp.getICMS().getICMS40().getCST();
                this.valorIcms = imp.getICMS().getICMS40().getVICMS();
            } else if (imp.getICMS().getICMS51() != null) {
                this.aliquotaIcms = imp.getICMS().getICMS51().getPICMS();
                this.baseIcms     = imp.getICMS().getICMS51().getVBC();
                this.valorIcms    = imp.getICMS().getICMS51().getVICMS();
                this.cst          = imp.getICMS().getICMS51().getOrig() + imp.getICMS().getICMS51().getCST();
            } else if (imp.getICMS().getICMS60() != null) {
                this.cst = imp.getICMS().getICMS60().getOrig() + imp.getICMS().getICMS60().getCST();
            } else if (imp.getICMS().getICMS70() != null) {
                this.cst = imp.getICMS().getICMS70().getOrig() + imp.getICMS().getICMS70().getCST();
            } else if (imp.getICMS().getICMS90() != null) {
                this.cst = imp.getICMS().getICMS90().getOrig() + imp.getICMS().getICMS90().getCST();
            } else if (imp.getICMS().getICMSSN101() != null) {
                this.cst = imp.getICMS().getICMSSN101().getOrig() + imp.getICMS().getICMSSN101().getCSOSN();
            } else if (imp.getICMS().getICMSSN102() != null) {
                this.cst = imp.getICMS().getICMSSN102().getOrig() + imp.getICMS().getICMSSN102().getCSOSN();
            } else if (imp.getICMS().getICMSSN201() != null) {
                this.cst = imp.getICMS().getICMSSN201().getOrig() + imp.getICMS().getICMSSN201().getCSOSN();
            } else if (imp.getICMS().getICMSSN202() != null) {
                this.cst = imp.getICMS().getICMSSN202().getOrig() + imp.getICMS().getICMSSN202().getCSOSN();
            } else if (imp.getICMS().getICMSSN500() != null) {
                this.cst = imp.getICMS().getICMSSN500().getOrig() + imp.getICMS().getICMSSN500().getCSOSN();
            } else if (imp.getICMS().getICMSSN900().getOrig() != null) {
                this.aliquotaIcms = imp.getICMS().getICMSSN900().getPICMS();
                this.baseIcms = imp.getICMS().getICMSSN900().getVBC();
                this.valorIcms = imp.getICMS().getICMSSN900().getVICMS();
                this.cst = imp.getICMS().getICMSSN900().getOrig() + imp.getICMS().getICMSSN900().getCSOSN();
            }
        }
        this.infCompl = detalhe.getInfAdProd();
    }

    public String getAliquotaIcms() {
        return aliquotaIcms;
    }

    public void setAliquotaIcms(String aliquotaIcms) {
        this.aliquotaIcms = aliquotaIcms;
    }

    public String getAliquotaIpi() {
        return aliquotaIpi;
    }

    public void setAliquotaIpi(String aliquotaIpi) {
        this.aliquotaIpi = aliquotaIpi;
    }

    public String getBaseIcms() {
        return baseIcms;
    }

    public void setBaseIcms(String baseIcms) {
        this.baseIcms = baseIcms;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCst() {
        return cst;
    }

    public void setCst(String cst) {
        this.cst = cst;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getQuantidadeComercial() {
        return quantidadeComercial;
    }

    public void setQuantidadeComercial(String quantidadeComercial) {
        this.quantidadeComercial = quantidadeComercial;
    }

    public String getQuantidadeTributaria() {
        return quantidadeTributaria;
    }

    public void setQuantidadeTributaria(String quantidadeTributaria) {
        this.quantidadeTributaria = quantidadeTributaria;
    }

    public String getUnidadeComercial() {
        return unidadeComercial;
    }

    public void setUnidadeComercial(String unidadeComercial) {
        this.unidadeComercial = unidadeComercial;
    }

    public String getUnidadeTributaria() {
        return unidadeTributaria;
    }

    public void setUnidadeTributaria(String unidadeTributaria) {
        this.unidadeTributaria = unidadeTributaria;
    }

    public String getValorIcms() {
        return valorIcms;
    }

    public void setValorIcms(String valorIcms) {
        this.valorIcms = valorIcms;
    }

    public String getValorIpi() {
        return valorIpi;
    }

    public void setValorIpi(String valorIpi) {
        this.valorIpi = valorIpi;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getValorUnitarioComercial() {
        return valorUnitarioComercial;
    }

    public void setValorUnitarioComercial(String valorUnitarioComercial) {
        this.valorUnitarioComercial = valorUnitarioComercial;
    }

    public String getValorUnitarioTributario() {
        return valorUnitarioTributario;
    }

    public void setValorUnitarioTributario(String valorUnitarioTributario) {
        this.valorUnitarioTributario = valorUnitarioTributario;
    }

    public String getInfCompl() {
        return infCompl;
    }

    public void setInfCompl(String infCompl) {
        this.infCompl = infCompl;
    }
}
