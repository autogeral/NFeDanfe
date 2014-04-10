/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.jcomputacao.nfe.danfe;

import br.com.jcomputacao.nfe.danfe.util.StringUtil;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det.Imposto;


/**
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
    private String infCompl;

    public NfeDanfeItem() {

    }

    public NfeDanfeItem(Det detalhe) {
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
        Imposto imp = detalhe.getImposto();
        if(imp != null) {
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
//                this.aliquotaIcms = imp.getICMS().getICMS30().getAliquotaImposto();
//                this.baseIcms = imp.getICMS().getICMS30().getValorBCdoICMS();
//                this.valorIcms = imp.getICMS().getICMS30().getValorICMS();
            } else if (imp.getICMS().getICMS40() != null) {
                this.cst = imp.getICMS().getICMS40().getOrig() + imp.getICMS().getICMS40().getCST();
//                this.aliquotaIcms = imp.getICMS().getICMS40().getAliquotaImposto();
//                this.baseIcms = imp.getICMS().getICMS40().getValorBCdoICMS();
//                this.valorIcms = imp.getICMS().getICMS40().getValorICMS();
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
