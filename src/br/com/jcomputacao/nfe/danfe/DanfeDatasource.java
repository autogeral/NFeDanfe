package br.com.jcomputacao.nfe.danfe;

import java.util.List;
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
