/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.jcomputacao.nfe.danfe;

import java.util.ArrayList;
import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * 11/07/2010 16:55:41
 * @author Murilo
 */
public class DanfeItemDatasource extends JRAbstractBeanDataSourceProvider {
    private List<NfeDanfeItem> list = null;

    public DanfeItemDatasource () {
        super(NfeDanfeItem.class);
        this.list = new ArrayList<NfeDanfeItem>();
        for(int i=0;i<10;i++) {
            this.list.add(itemFake());
        }

    }

    public DanfeItemDatasource(List<NfeDanfeItem> itens) {
        super(NfeDanfeItem.class);
        this.list = itens;
    }

    public DanfeItemDatasource(NfeDanfeItem item) {
        super(NfeDanfeItem.class);
        this.list = new ArrayList<NfeDanfeItem>();
    }

    public JRDataSource create(JasperReport arg0) throws JRException {
        return new JRBeanCollectionDataSource(list);
    }

    public void dispose(JRDataSource arg0) throws JRException {

    }

    public JRBeanCollectionDataSource getDataSource(){
        return new JRBeanCollectionDataSource(this.list);
    }

    private NfeDanfeItem itemFake() {
        NfeDanfeItem ndi = new NfeDanfeItem();
        ndi.setCodigo("CODIGO");
        ndi.setDescricao("FERRARI 360");
        ndi.setNcm("97800010");
        ndi.setCfop("5102");
        ndi.setUnidadeComercial("PC");
        ndi.setUnidadeTributaria("PC");
        ndi.setCst("060");
        ndi.setAliquotaIpi("5");
        ndi.setValorIpi("5,00");
        ndi.setBaseIcms("100,00");
        ndi.setAliquotaIcms("18");
        ndi.setValorIcms("18,00");
        ndi.setQuantidadeComercial("1,00");
        ndi.setQuantidadeTributaria("1,00");
        ndi.setValorUnitarioComercial("100,00");
        ndi.setValorUnitarioTributario("100,00");
        ndi.setValorTotal("100,00");
        ndi.setInfCompl("Informações complementares");
        return ndi;
    }

}
