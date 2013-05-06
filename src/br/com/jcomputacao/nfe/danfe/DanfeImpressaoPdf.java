package br.com.jcomputacao.nfe.danfe;

import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TEndereco;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Det.Imposto;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Ide;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNFe.InfNFe.Transp.Vol;
import br.inf.portalfiscal.nfe.xml.pl006q.nfes.TNfeProc;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author William
 */
public class DanfeImpressaoPdf {

    private Document document = null;
    private PdfWriter writer = null;
    private Font headerFont = new Font(Font.HELVETICA, 11, Font.BOLD);
    private Font headerSubFont = new Font(Font.HELVETICA, 10, Font.NORMAL);
    private Font titleFont = new Font(Font.HELVETICA, 14, Font.BOLD);
    private Font normalFont = new Font(Font.HELVETICA, 8, Font.NORMAL);
    private Font normalBold = new Font(Font.HELVETICA, 5, Font.BOLD);
    private Font lowerBold = new Font(Font.HELVETICA, 4, Font.BOLD);
    private Font upperFont = new Font(Font.HELVETICA, 3, Font.NORMAL);
    private Font itemFont = new Font(Font.HELVETICA, 5, Font.NORMAL);
    private Font smallFont = new Font(Font.HELVETICA, 4, Font.NORMAL);
    private Font subTitle = new Font(Font.HELVETICA, 7, Font.BOLD);
    private List<TNfeProc> nfes = null;
    private TNfeProc atual;
    private Det detalhe;

    public DanfeImpressaoPdf(TNfeProc nfe) {
        this.nfes = new ArrayList<TNfeProc>();
        this.nfes.add(nfe);
    }

    public DanfeImpressaoPdf(List<TNfeProc> nfes) {
        this.nfes = nfes;
    }

    public ByteArrayOutputStream execute() throws DocumentException, IOException {
        document = new Document(PageSize.A4);
        document.setMargins(20f, 20f, 20f, 20f);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        writer = PdfWriter.getInstance(document, baos);

        document.open();
        document.addTitle("DANFE");
        document.addSubject("Documento Auxiliar da Nota Fiscal Eletronica");

        for (TNfeProc nfe : nfes) {
            this.atual = nfe;
            if(validar()) {
                printNfe();
            } else {
                System.out.println("Não conseguiu validar a NFe " + 
                        (nfe.getProtNFe() != null ? 
                        (nfe.getProtNFe().getInfProt() != null ? nfe.getProtNFe().getInfProt().getChNFe() : "")
                        : ""));
            }
        }

        document.close();
        return baos;
    }
    
    private boolean validar() {
        if(atual == null) {
            return false;
        }
        if (atual.getNFe() == null) {
            return false;
        }
        return true;
    }

    private void printNfe() throws DocumentException {
        if (this.document == null || this.writer == null) {
            throw new IllegalStateException("Documento pdf e writer em estados inválidos");
        }

        if (this.atual == null) {
            throw new IllegalStateException("Sem nfe atual");
        }



//        class RoundRectangle implements PdfPCellEvent {  
///** 
// * @see com.lowagie.text.pdf.PdfPCellEvent#cellLayout(com.lowagie.text.pdf.PdfPCell, 
// *      com.lowagie.text.Rectangle, 
// *      com.lowagie.text.pdf.PdfContentByte[]) 
// */  
//            @Override
//            public void cellLayout(PdfPCell cell, Rectangle rect,  
//            PdfContentByte[] canvas) {  
//            PdfContentByte cb = canvas[PdfPTable.LINECANVAS];  
//            cb.setColorStroke(new GrayColor(0.8f));  
//            cb.roundRectangle(rect.getLeft() + 4, rect.getBottom(), rect.getWidth() - 8,  
//            rect.getHeight() - 4, 25);  
//            cb.stroke();  
//            }  
//            }   



        PdfPTable table;
        PdfPCell cell8;




        //1ºlinha



        table = new PdfPTable(new float[]{0.78f, 0.22f});
        PdfPCell cell;
        table.setWidthPercentage(100);
        cell = new PdfPCell(new Phrase("RECEBEMOS DE      OS PRODUTOS/SERVIÇOS CONSTANTES DA NOTA FISCAL INDICADA AO LADO\n\n\n ", upperFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(getSpace() + "NF-e \n\n\n    N° " + getNum(), upperFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setBorder(13);
        table.addCell(cell);

        document.add(table);


        table = new PdfPTable(new float[]{0.22f, 0.56f, 0.22f});
        table.setWidthPercentage(100);
        cell = new PdfPCell(new Phrase(""));
        cell = new PdfPCell(new Phrase("DATA DE RECEBIMENTO\n\n\n", upperFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("IDENTIFICAÇÃO E ASSINATURA DO RECEBEDOR\n\n\n", upperFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("    SÉRIE : " + getSerie() + "\n\n\n", upperFont));
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(14);
        table.addCell(cell);
        document.add(table);


        // linha 
        PdfContentByte cb = writer.getDirectContent();
        cb.setLineWidth(0f);
        float y = document.getPageSize().getHeight() - 53;
        cb.moveTo(20, y);
        cb.lineTo(document.getPageSize().getWidth() - 20, y);
        cb.stroke();




        table = new PdfPTable(new float[]{0.45f, 0.20f, 0.35f});
        cell.setMinimumHeight(60);
        table.setSpacingBefore(8);
        table.setWidthPercentage(100);
        PdfPTable table2 = new PdfPTable(new float[]{0.2f, 0.8f});
        PdfPCell cell2 = new PdfPCell();
        PdfPCell cell3 = new PdfPCell(new Phrase("\n\n" + getNomeEmit(), normalBold));


        Phrase frase1 = new Phrase(getNomeEmit(), normalBold);
        cell3.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        cell3.setVerticalAlignment(Phrase.ALIGN_CENTER);


        cell2.setBorder(0);
        table2.addCell(cell2);
        cell3.setBorder(0);
        table2.addCell(cell3);
        table.setWidthPercentage(100);

        Phrase frase2 = new Phrase(getEnderEmit(), normalBold);
        Paragraph p2 = new Paragraph(frase2);
        p2.setAlignment(Phrase.ALIGN_CENTER);

        frase1 = new Phrase("\n\n\n\n\n" + getNomeEmit(), normalBold);
        Paragraph p1 = new Paragraph(frase1);
        Paragraph spc = new Paragraph("");
        p1.setAlignment(Phrase.ALIGN_LEFT);

        cell2.addElement(p1);
        cell = new PdfPCell();
        cell.addElement(table2);
        cell.addElement(spc);
        cell.addElement(p2);
        cell.setMinimumHeight(80);
        table.addCell(cell);
        document.add(table);


        cell = new PdfPCell();
        cell.setHorizontalAlignment(Phrase.ALIGN_CENTER);
        frase1 = new Phrase("DANFE", subTitle);
        p1 = new Paragraph(frase1);
        p1.setAlignment(Phrase.ALIGN_CENTER);
        frase2 = new Phrase("Documento Auxiliar da Nota\n Fiscal Eletrônica\n\n", itemFont);
        p2 = new Paragraph(frase2);
        PdfPTable table1 = new PdfPTable(new float[]{0.4f, 0.15f, 0.4f});
        table1.setWidthPercentage(100);
        PdfPCell cell1 = new PdfPCell(new Phrase("0 - Entrada\n1 - Saída", itemFont));
        PdfPCell cell4 = new PdfPCell(new Phrase("  " + getEntradaSaida(), subTitle));
        PdfPCell cell5 = new PdfPCell(new Phrase(""));
        cell1.setBorder(0);
        table1.addCell(cell1);
        table1.addCell(cell4);
        cell5.setBorder(0);
        table1.addCell(cell5);

        Phrase frase4 = new Phrase("Nº " + getNum() + "\n" + "Série: " + getSerie(), subTitle);
        Paragraph p4 = new Paragraph(frase4);
        Phrase frase5 = new Phrase(getIde().getIndPag(), subTitle);
        Paragraph p5 = new Paragraph(frase5);
        p5.setAlignment(Phrase.ALIGN_CENTER);
        p2.setAlignment(Phrase.ALIGN_CENTER);
        cell.addElement(p1);
        cell.addElement(p2);
        cell.addElement(table1);
        cell.addElement(p4);
        cell.addElement(p5);
        cell.setBorder(12);
        cell.setMinimumHeight(80);
        table.addCell(cell);


        cell = new PdfPCell();
        cell.addElement(new Paragraph("CONTROLE DO FISCO\n\n", upperFont));
        Barcode128 code128 = new Barcode128();
        code128.setSize(5);
        code128.setCode(getCodigodeBarras());
        code128.setCodeType(Barcode.CODE128_RAW);
        code128.setSize(0.8f);
        cell.addElement(code128.createImageWithBarcode(cb, null, null));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setMinimumHeight(80);
        table1 = new PdfPTable(new float[]{1f});
        table1.setWidthPercentage(90);
        Paragraph p = new Paragraph(new Phrase(getChaveAcesso(), lowerBold));
        Paragraph p6 = new Paragraph(new Phrase("CHAVE DE ACESSO\n", upperFont));
        p.setAlignment(Phrase.ALIGN_CENTER);
        Paragraph p7 = new Paragraph("Consulta de autenticidade no portal nacional da NF-e www.nfe.fazenda.gov.br/portal ou no site da Sefaz Autorizada", itemFont);
        cell1 = new PdfPCell();
        cell1.addElement(p6);
        cell1.addElement(p);
        table1.addCell(cell1);
        cell.addElement(table1);
        cell.addElement(p7);
        table.addCell(cell);
        document.add(table);


        table = new PdfPTable(new float[]{0.58f, 0.42f});
        table.setWidthPercentage(100);
        table.setSpacingBefore(4);
        p = new Paragraph("NATUREZA DA OPERAÇÃO\n ", upperFont);
        p.setAlignment(Element.ALIGN_TOP);
        p.add(new Chunk(getNaturezaOperacao(), itemFont));
        cell = new PdfPCell(p);
        table.addCell(cell);
        p = new Paragraph("PROTOCOLO DE AUTORIZAÇÃO DE USO\n ", upperFont);
        p.add(new Chunk("                                                                 " + getProtocoloAutorizacao(), lowerBold));
        cell = new PdfPCell(p);
        table.addCell(p);
        document.add(table);

        table = new PdfPTable(new float[]{0.25f, 0.25f, 0.50f});
        table.setWidthPercentage(100);
        Phrase frase = new Phrase("INSCRIÇÃO ESTADUAL\n", upperFont);
        frase.add(new Chunk(getInscricaoEstadualEmit(), itemFont));
        cell = new PdfPCell(frase);
        table.addCell(frase);
        frase = new Phrase("INSCRIÇÃO ESTADUAL DO SUBST. TRIB.\n ", upperFont);
        frase.add(new Chunk(getInscricaoEstadualSubst(), itemFont));
        table.addCell(frase);
        frase = new Phrase("CNPJ\n", upperFont);
        frase.add(new Chunk(getCNPJEmit(), itemFont));
        table.addCell(frase);
        document.add(table);



        p = new Paragraph("DESTINATÁRIO/REMETENTE", normalBold);
        p.setSpacingAfter(2);
        document.add(p);

        table = new PdfPTable(new float[]{0.70f, 0.1339f, 0.1671f});
        table.setWidthPercentage(100);
        frase = new Phrase("NOME/RAZÃO SOCIAL\n", upperFont);
        frase.add(new Chunk(getNomeEmit(), itemFont));
        table.addCell(frase);
        frase = new Phrase("CNPJ/CPF\n", upperFont);
        frase.add(new Chunk(getCpfCnpjRemetente(), itemFont));
        table.addCell(frase);
        frase = new Phrase("DATA DA EMISSÃO\n", upperFont);
        frase.add(new Chunk(getDataEmissao(), itemFont));
        table.addCell(frase);
        document.add(table);


        table = new PdfPTable(new float[]{0.50f, 0.15f, 0.1f, 0.15f});
        table.setWidthPercentage(100);
        frase = new Phrase("ENDEREÇO\n ", upperFont);
        frase.add(new Chunk(getEnderecoDest(), itemFont));
        table.addCell(frase);
        frase = new Phrase("BAIRRO/DISTRITO\n ", upperFont);
        frase.add(new Chunk(getBairro(), itemFont));
        table.addCell(frase);
        frase = new Phrase("CEP\n", upperFont);
        frase.add(new Chunk(getCEP(), itemFont));
        table.addCell(frase);
        frase = new Phrase("DATA DE ENTRADA/SAÍDA\n", upperFont);
        frase.add(new Chunk(getDataEntradaSaida(), itemFont));
        table.addCell(frase);
        document.add(table);

        table = new PdfPTable(new float[]{0.40f, 0.15f, 0.05f, 0.15f, 0.15f});
        table.setWidthPercentage(100);
        frase = new Phrase("MUNÍCIPIO\n", upperFont);
        frase.add(new Chunk(getMunicipioDest(), itemFont));
        table.addCell(frase);
        frase = new Phrase("FONE/FAX\n", upperFont);
        frase.add(new Chunk(getFoneFax(), itemFont));
        table.addCell(frase);
        frase = new Phrase("UF\n", upperFont);
        frase.add(new Chunk(getUFDest(), itemFont));
        table.addCell(frase);
        frase = new Phrase("INSCRIÇÃO ESTADUAL\n", upperFont);
        frase.add(new Chunk(getInscricaoEstadualDest(), itemFont));
        table.addCell(frase);
        frase = new Phrase("HORA DE ENTRADA/SAÍDA\n", upperFont);
        frase.add(new Chunk(getHoraEntradaSaida(), itemFont));
        table.addCell(frase);
        document.add(table);




        p = new Paragraph("FATURA", normalBold);
        p.setSpacingAfter(2);
        document.add(p);
        table = new PdfPTable(new float[]{1f});
        table.setWidthPercentage(100);
        cell = new PdfPCell(new Phrase(getFatura(), normalFont));
        table.addCell(cell);
        document.add(table);

        p = new Paragraph("CÁLCULO DO IMPOSTO", normalBold);
        p.setSpacingAfter(2);
        document.add(p);
        table = new PdfPTable(new float[]{0.18f, 0.18f, 0.28f, 0.18f, 0.18f});
        table.setWidthPercentage(100);
        frase = new Phrase("BASE DE CÁLCULO DO ICMS\n", upperFont);
        frase.add(new Chunk(getBaseCalculoICMS(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR DO ICMS\n", upperFont);
        frase.add(new Chunk(getValorICMS(), itemFont));
        table.addCell(frase);
        frase = new Phrase("BASE DE CÁLCULO DO ICMS ST\n", upperFont);
        frase.add(new Chunk(getBaseCalculoICMSST(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR DO ICMS ST\n", upperFont);
        frase.add(new Chunk(getValorICMSST(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR TOTAL DOS PRODUTOS\n", upperFont);
        frase.add(new Chunk(getValorTotalProdutos(), itemFont));
        table.addCell(frase);
        document.add(table);

        table = new PdfPTable(new float[]{0.16f, 0.16f, 0.16f, 0.16f, 0.16f, 0.16f});
        table.setWidthPercentage(100);
        frase = new Phrase("VALOR DO FRETE\n", upperFont);
        frase.add(new Chunk(getValorFrete(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR DO SEGURO\n", upperFont);
        frase.add(new Chunk(getValorSeguro(), itemFont));

        table.addCell(frase);
        frase = new Phrase("DESCONTO\n", upperFont);
        frase.add(new Chunk(getDesconto(), itemFont));
        table.addCell(frase);
        frase = new Phrase("OUTRAS DESPESAS ACESSÓRIAS\n", upperFont);
        frase.add(new Chunk(getOutrasDespesas(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR DO IPI\n", upperFont);
        frase.add(new Chunk(getValorIPI(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR TOTAL DA NOTA\n", upperFont);
        frase.add(new Chunk(getValorTotalNota(), itemFont));
        table.addCell(frase);
        document.add(table);

        p = new Paragraph("TRANSPORTADOR/VOLUMES TRANSPORTADOS", normalBold);
        p.setSpacingAfter(2);

        document.add(p);
        table = new PdfPTable(new float[]{0.3f, 0.15f, 0.15f, 0.15f, 0.05f, 0.20f});
        table.setWidthPercentage(100);
        frase = new Phrase("RAZÃO SOCIAL\n", upperFont);
        frase.add(new Chunk(getRazaoSocialTransp(), itemFont));
        table.addCell(frase);
        frase = new Phrase("FRETE POR CONTA\n", upperFont);
        frase.add(new Chunk(getFrete(), itemFont));
        table.addCell(frase);
        frase = new Phrase("CÓDIGO ANTT\n", upperFont);
        frase.add(new Chunk(getCodigoANTT(), itemFont));
        table.addCell(frase);
        frase = new Phrase("PLACA DO VEÍCULO\n", upperFont);
        frase.add(new Chunk(getPlacaVeiculo(), itemFont));
        table.addCell(frase);
        frase = new Phrase("UF\n", upperFont);
        frase.add(new Chunk(getUFTransporta(), itemFont));
        table.addCell(frase);
        frase = new Phrase("CNPJ/CPF\n", upperFont);
        frase.add(new Chunk(getCNPJTransportador(), itemFont));
        table.addCell(frase);
        document.add(table);

        table = new PdfPTable(new float[]{0.40f, 0.35f, 0.05f, 0.2f});
        cell = new PdfPCell(new Phrase(""));
        table.setWidthPercentage(100);
        frase = new Phrase("ENDEREÇO\n", upperFont);
        frase.add(new Chunk(getEnderecoTransporta(), itemFont));
        table.addCell(frase);
        frase = new Phrase("MUNICÍPIO\n", upperFont);
        frase.add(new Chunk(getMunicipioTransporta(), itemFont));
        table.addCell(frase);
        frase = new Phrase("UF\n", upperFont);
        frase.add(new Chunk(getUFDest(), itemFont));
        table.addCell(frase);
        frase = new Phrase("INSCRIÇÃO ESTADUAL\n", upperFont);
        frase.add(new Chunk(getInscricaoTransporta(), itemFont));
        table.addCell(frase);
        document.add(table);

        table = new PdfPTable(new float[]{0.10f, 0.19f, 0.19f, 0.10f, 0.10f, 0.10f});
        table.setWidthPercentage(100);
        frase = new Phrase("QUANTIDADE\n", upperFont);
        frase.add(new Chunk(getQuantidadeVolume(), itemFont));
        table.addCell(frase);
        frase = new Phrase("ESPÉCIE\n", upperFont);
        frase.add(new Chunk(getEspecie(), itemFont));
        table.addCell(frase);
        frase = new Phrase("MARCA\n", upperFont);
        frase.add(new Chunk(getMarca(), itemFont));
        table.addCell(frase);
        frase = new Phrase("NUMERAÇÃO\n", upperFont);
        frase.add(new Chunk(getNumeracao(), itemFont));
        table.addCell(frase);
        frase = new Phrase("PESO BRUTO\n", upperFont);
        frase.add(new Chunk(getPesoBruto(), itemFont));
        table.addCell(frase);
        frase = new Phrase("PESO LÍQUIDO\n", upperFont);
        frase.add(new Chunk(getPesoLiquido(), itemFont));
        table.addCell(frase);
        document.add(table);

        p = new Paragraph("DADOS DO PRODUTO/SERVIÇO", normalBold);
        p.setSpacingAfter(2);
        document.add(p);
        table = new PdfPTable(new float[]{0.05f, 0.4f, 0.06f, 0.03f, 0.03f, 0.03f, 0.03f, 0.06f, 0.06f, 0.06f, 0.06f, 0.06f, 0.03f, 0.03f});
        table.setWidthPercentage(100);
        cell = new PdfPCell(new Phrase("CÓDIGO\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("DESCRIÇÃO DO PRODUTO/SERVIÇO\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("NCM/SH\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("CST\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("CFOP\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("UNID.\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("QTD.\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("VLR. UNIT.\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("VLR. TOTAL\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("BC ICMS\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("VLR. ICMS\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("VLR. IPI\n", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("ALÍQ.\nICMS", smallFont));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("ALÍQ.\nIPI", smallFont));
        table.addCell(cell);
        document.add(table);

        table = new PdfPTable(new float[]{0.05f, 0.4f, 0.06f, 0.03f, 0.03f, 0.03f, 0.03f, 0.06f, 0.06f, 0.06f, 0.06f, 0.06f, 0.03f, 0.03f});
        table.setWidthPercentage(100);

        List<Det> dets = this.atual.getNFe().getInfNFe().getDet();
        for (Det det : dets) {
            setDetalhe(det);
            cell = new PdfPCell(new Phrase(getCodigo(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getDescricaoProdutoServico(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getNCMSH(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getCST(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getCFOP(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getUnid(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getQtd(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getVlrUnit(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getVlrTotal(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getBCICMS(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getVlrICMS(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getVlrIPI(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getVlrAliqICMS(), smallFont));
            table.addCell(cell);
            cell = new PdfPCell(new Phrase(getAliqIPI(), smallFont));
            table.addCell(cell);
        }

        document.add(table);



        p = new Paragraph("");
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);
        document.add(p);


        p = new Paragraph("CÁLCULO DO ISSQN", normalBold);
        document.add(p);
        table.setSpacingBefore(2);
        table = new PdfPTable(new float[]{0.25f, 0.25f, 0.25f, 0.25f});
        table.setWidthPercentage(100);
        frase = new Phrase("INSCRIÇÃO MUNICIPAL\n", upperFont);
        frase.add(new Chunk(getInscricaoMunicipal(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR TOTAL DOS SERVIÇOS\n", upperFont);
        frase.add(new Chunk(getValorTotalServicos(), itemFont));
        table.addCell(frase);
        frase = new Phrase("BASE DE CÁLCULO DO ISSQN\n", upperFont);
        frase.add(new Chunk(getBaseCalculoISSQN(), itemFont));
        table.addCell(frase);
        frase = new Phrase("VALOR DO ISSQN\n", upperFont);
        frase.add(new Chunk(getValorISSQN(), itemFont));
        table.addCell(frase);
        document.add(table);


        p = new Paragraph("DADOS ADICIONAIS", normalBold);
        p.setSpacingAfter(2);
        document.add(p);



        table = new PdfPTable(new float[]{0.5f, 0.5f});
        table.setSpacingBefore(7);
        table.setWidthPercentage(100);
        cell = new PdfPCell(new Phrase("INFORMAÇÕES COMPLEMENTARES ", upperFont));
        cell.setMinimumHeight(80);

        table.addCell(cell);
        cell = new PdfPCell(new Phrase("RESERVADO AO FISCO ", upperFont));
        cell.setMinimumHeight(80);
        table.addCell(cell);


        document.add(table);

    }

    private String getSpace() {
        return "                                                                  ";
    }

    private String getNum() {
        if (getIde().getNNF() == null) {
            return "";
        } else {
            return getIde().getNNF();
        }
    }

    private String getNomeEmit() {
        if (getInfNFe().getEmit() == null || getInfNFe().getEmit().getXNome() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getXNome();
        }
    }

    public String getSerie() {
        if (getIde().getSerie() == null) {
            return "";
        } else {
            return getIde().getSerie();
        }
    }

    public String getEnderEmit() {

        return getInfNFe().getEmit().getEnderEmit().getXLgr() + ", " + getInfNFe().getEmit().getEnderEmit().getNro() + "\n" + getInfNFe().getEmit().getEnderEmit().getXBairro() + ", " + getInfNFe().getEmit().getEnderEmit().getXMun() + ", " + getInfNFe().getEmit().getEnderEmit().getUF() + " - " + "CEP: " + getInfNFe().getEmit().getEnderEmit().getCEP();

    }

    public String getTipo() {
        if (getIde().getTpEmis() == null) {
            return "";
        } else {
            return getIde().getTpEmis();
        }
    }

    public String getFatura() {
        if (getInfNFe().getCobr() != null
                && getInfNFe().getCobr().getFat() != null
                && getInfNFe().getCobr().getFat().getNFat() != null) {
            return getInfNFe().getCobr().getFat().getNFat();
        } else {
            return "";
        }
    }

    public String getNaturezaOperacao() {
        if (getIde().getNatOp() == null) {
            return "";
        } else {
            return getIde().getNatOp();
        }
    }

    public String getProtocoloAutorizacao() {
        if (atual.getProtNFe().getInfProt().getNProt() == null) {
            return "";
        } else {
            return atual.getProtNFe().getInfProt().getNProt();
        }
    }

    public String getInscricaoEstadualEmit() {
        if (getInfNFe().getEmit() == null || getInfNFe().getEmit().getIE() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getIE();
        }
    }

    public String getInscricaoEstadualSubst() {
        if (getInfNFe().getEmit().getIEST() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getIEST();
        }
    }

    public String getCNPJEmit() {
        if (getInfNFe().getEmit() == null || getInfNFe().getEmit().getCNPJ() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getCNPJ();
        }
    }

    public String getCpfCnpjRemetente() {
        if (getInfNFe().getEmit() == null || getInfNFe().getEmit().getCNPJ() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getCNPJ();
        }
    }

    public String getDataEmissao() {
        if (getIde().getDEmi() == null) {
            return "";
        } else {
            return getIde().getDEmi();
        }
    }

    public String getBairro() {
        if (getInfNFe().getDest().getEnderDest() == null || getInfNFe().getDest().getEnderDest().getXBairro() == null) {
            return "";
        } else {
            return getInfNFe().getDest().getEnderDest().getXBairro();
        }
    }

    public String getCEP() {

        if (getInfNFe().getDest() == null || getInfNFe().getDest().getEnderDest() == null || getInfNFe().getDest().getEnderDest().getCEP() == null) {
            return "";
        } else {
            return getInfNFe().getDest().getEnderDest().getCEP();
        }
    }

    public String getFoneFax() {
        if (getInfNFe().getDest().getEnderDest() == null || getInfNFe().getDest() == null || getInfNFe().getDest().getEnderDest().getFone() == null) {
            return "";
        } else {
            return getInfNFe().getDest().getEnderDest().getFone();
        }
    }

    public String getUFEmit() {
        if (getInfNFe().getEmit().getEnderEmit() == null || getInfNFe().getEmit().getEnderEmit().getUF().value() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getEnderEmit().getUF().value();

        }
    }

    public String getDataEntradaSaida() {
        if (getIde().getDSaiEnt() == null) {
            return "";
        } else {
            return getIde().getDSaiEnt();
        }
    }

    public String getBaseCalculoICMS() {
        if (getInfNFe().getTotal().getICMSTot().getVBC() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVBC();
        }
    }

    public String getValorICMS() {
        if (getInfNFe().getTotal().getICMSTot().getVICMS() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVICMS();
        }
    }

    public String getBaseCalculoICMSST() {
        if (getInfNFe().getTotal().getICMSTot().getVBCST() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVBCST();
        }
    }

    public String getValorICMSST() {
        if (getInfNFe().getTotal().getICMSTot().getVST() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVST();
        }
    }

    public String getValorTotalProdutos() {
        if (getInfNFe().getTotal().getICMSTot().getVProd() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVProd();
        }
    }

    public String getValorFrete() {
        if (getInfNFe().getTotal().getICMSTot().getVFrete() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVFrete();
        }
    }

    public String getValorSeguro() {
        if (getInfNFe().getTotal().getICMSTot().getVSeg() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVSeg();
        }
    }

    public String getDesconto() {
        if (getInfNFe().getTotal().getICMSTot().getVDesc() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVDesc();
        }
    }

    public String getOutrasDespesas() {
        if (getInfNFe().getTotal().getICMSTot().getVOutro() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVOutro();
        }
    }

    public String getValorIPI() {
        if (getInfNFe().getTotal().getICMSTot().getVIPI() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVIPI();
        }
    }

    public String getValorTotalNota() {
        if (getInfNFe().getTotal().getICMSTot().getVNF() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getICMSTot().getVNF();
        }
    }

    public String getFrete() {
        if (getInfNFe().getTransp().getModFrete() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getModFrete();
        }
    }

    public String getRazaoSocialTransp() {
        if (getInfNFe().getTransp().getTransporta() == null
                || getInfNFe().getTransp().getTransporta().getXNome() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getTransporta().getXNome();
        }
    }

    public String getCodigoANTT() {
        if (getInfNFe().getTransp() == null
                || getInfNFe().getTransp().getVeicTransp() == null
                || getInfNFe().getTransp().getVeicTransp().getRNTC() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getVeicTransp().getRNTC();
        }
    }

    public String getPlacaVeiculo() {
        if (getInfNFe().getTransp() == null
                || getInfNFe().getTransp().getVeicTransp() == null
                || getInfNFe().getTransp().getVeicTransp().getPlaca() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getVeicTransp().getPlaca();
        }
    }

    public String getCNPJTransportador() {
        if (getInfNFe().getTransp() == null
                || getInfNFe().getTransp().getTransporta() == null
                || getInfNFe().getTransp().getTransporta().getCNPJ() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getTransporta().getCNPJ();
        }
    }

    public String getEnderecoDest() {
        TEndereco endDest = getInfNFe().getDest().getEnderDest();
        return endDest.getXLgr() + (endDest.getNro() != null ? ", " + endDest.getNro() : "");
    }

    public String getEnderecoTransporta() {
        if (getInfNFe().getTransp().getTransporta() == null || getInfNFe().getTransp().getTransporta().getXEnder() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getTransporta().getXEnder();
        }
    }

    public String getMunicipioTransporta() {
        if (getInfNFe().getTransp().getTransporta() == null || getInfNFe().getTransp().getTransporta().getXMun() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getTransporta().getXMun();
        }
    }

    public String getUFTransporta() {
        if (getInfNFe().getTransp().getTransporta() == null || getInfNFe().getTransp().getTransporta().getUF().value() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getTransporta().getUF().value();

        }
    }

    public String getInscricaoTransporta() {
        if (getInfNFe().getTransp().getTransporta() == null || getInfNFe().getTransp().getTransporta().getIE() == null) {
            return "";
        } else {
            return getInfNFe().getTransp().getTransporta().getIE();
        }
    }

    public String getUFDest() {
        if (getInfNFe().getDest() == null || getInfNFe().getDest().getEnderDest() == null || getInfNFe().getDest().getEnderDest().getUF().value() == null) {
            return "";
        } else {
            return getInfNFe().getDest().getEnderDest().getUF().value();

        }
    }

    public String getMunicipioDest() {
        if (getInfNFe().getDest() == null || getInfNFe().getDest().getEnderDest() == null || getInfNFe().getDest().getEnderDest().getXMun() == null) {
            return "";
        } else {
            return getInfNFe().getDest().getEnderDest().getXMun();
        }
    }

    public String getInscricaoEstadualDest() {
        return getInfNFe().getDest().getIE();

    }

    public String getQuantidadeVolume() {
        List<Vol> volumes = getInfNFe().getTransp().getVol();
        int qtdVolumes = 0;
        if (volumes != null && !volumes.isEmpty()) {
            for (Vol vol : volumes) {
                qtdVolumes += Integer.parseInt(vol.getQVol());
            }
        }
        return Integer.toString(qtdVolumes);
    }

    public String getEspecie() {
        String esp = "";
        if (getInfNFe() != null && getInfNFe().getTransp() != null && getInfNFe().getTransp().getVol() != null) {

            List<Vol> volumes = getInfNFe().getTransp().getVol();
            if (!volumes.isEmpty()) {
                boolean first = true;
                for (Vol vol : volumes) {
                    if (vol.getEsp() != null && "".equals(vol.getEsp().trim())) {
                        if (first) {
                            first = false;
                        } else {
                            esp += ",";
                        }
                        esp += vol.getEsp();
                    }
                }
            }
        }

        return esp;
    }

    public String getMarca() {
        String marca = "";
        if (getInfNFe() != null && getInfNFe().getTransp() != null
                && getInfNFe().getTransp().getVol() != null) {
            List<Vol> volumes = getInfNFe().getTransp().getVol();
            if (!volumes.isEmpty()) {
                boolean first = true;
                for (Vol vol : volumes) {
                    if (vol.getMarca() != null && "".equals(vol.getMarca().trim())) {
                        if (first) {
                            first = false;
                        } else {
                            marca += ",";
                        }
                        marca += vol.getMarca();
                    }
                }
            }
        }

        return marca;
    }

    public String getNumeracao() {
        String nVol = "";
        if (getInfNFe() != null && getInfNFe().getTransp() != null
                && getInfNFe().getTransp().getVol() != null) {
            List<Vol> volumes = getInfNFe().getTransp().getVol();
            int numeroVolumes = 0;
            if (!volumes.isEmpty()) {
                for (Vol vol : volumes) {
                    if (vol.getNVol() != null && "".equals(vol.getNVol().trim())) {
                        numeroVolumes += Integer.parseInt(vol.getNVol());
                    }
                }
            }
        }
        return nVol;
    }

    public String getPesoBruto() {
        List<Vol> volumes = getInfNFe().getTransp().getVol();
        double pesoB = 0;
        if (volumes != null && !volumes.isEmpty()) {
            for (Vol vol : volumes) {
                pesoB += Double.parseDouble(vol.getPesoB());
            }
        }
        return Double.toString(pesoB);
    }

    public String getPesoLiquido() {
        List<Vol> volumes = getInfNFe().getTransp().getVol();
        double pesoL = 0;
        if (volumes != null && !volumes.isEmpty()) {
            for (Vol vol : volumes) {
                pesoL += Double.parseDouble(vol.getPesoL());
            }
        }
        return Double.toString(pesoL);


    }

    public String getCodigo() {
        List<Det> detalhes = getInfNFe().getDet();
        String cProd = "";
        //if (detalhes != null && !detalhes.isEmpty()) {
        if (detalhe != null) {
            cProd = detalhe.getProd().getCProd();
        }
        return cProd;

    }

    public String getDescricaoProdutoServico() {
        List<Det> detalhes = getInfNFe().getDet();
        String cProd = "";
//        if (detalhes != null && !detalhes.isEmpty()) {
//            for (Det det : detalhes) {
        if (detalhe != null) {
                cProd = detalhe.getProd().getCProd();
//            }
        }
        return cProd;
    }

    public String getNCMSH() {
        List<Det> detalhes = getInfNFe().getDet();
        String NCM = "";
//        if (detalhes != null && !detalhes.isEmpty()) {
//            for (Det det : detalhes) {
        if (detalhe != null) {
                NCM = detalhe.getProd().getNCM();
            }
//        }
        return NCM;
    }

    public String getCST() {
        List<Det> detalhes = getInfNFe().getDet();
        String CST = "";
//        if (detalhes != null && !detalhes.isEmpty()) {
//            for (Det det : detalhes) {
        if (detalhe != null) {
            Imposto imposto = detalhe.getImposto();
            if (imposto.getICMS().getICMS00() != null) {
                CST = imposto.getICMS().getICMS00().getCST();
            }
            if (imposto.getICMS().getICMS20() != null) {
                CST = imposto.getICMS().getICMS20().getCST();
            }
            if (imposto.getICMS().getICMS30() != null) {
                CST = imposto.getICMS().getICMS30().getCST();
            }
            if (imposto.getICMS().getICMS40() != null) {
                CST = imposto.getICMS().getICMS40().getCST();
            }
            if (imposto.getICMS().getICMS51() != null) {
                CST = imposto.getICMS().getICMS51().getCST();
            }
            if (imposto.getICMS().getICMS60() != null) {
                CST = imposto.getICMS().getICMS60().getCST();
            }
            if (imposto.getICMS().getICMS90() != null) {
                CST = imposto.getICMS().getICMS90().getCST();
            }
        }
//        }
        return CST;
    }

    public String getCFOP() {
        List<Det> detalhes = getInfNFe().getDet();
        String CFOP = "";
        if (detalhes != null && !detalhes.isEmpty()) {
            for (Det det : detalhes) {
                CFOP = det.getProd().getCFOP();
            }
        }
        return CFOP;
    }

    public String getUnid() {
        List<Det> detalhes = getInfNFe().getDet();
        String uCom = "";
        if (detalhes != null && !detalhes.isEmpty()) {
            for (Det det : detalhes) {
                uCom = det.getProd().getUCom();
            }
        }
        return uCom;
    }

    public String getQtd() {
        List<Det> detalhes = getInfNFe().getDet();
        String qCom = "";
        if (detalhes != null && !detalhes.isEmpty()) {
            for (Det det : detalhes) {
                qCom = det.getProd().getQCom();
            }
        }
        return qCom;
    }

    public String getVlrUnit() {
        List<Det> detalhes = getInfNFe().getDet();
        String vUnCom = "";
        if (detalhes != null && !detalhes.isEmpty()) {
            for (Det det : detalhes) {
                vUnCom = det.getProd().getVUnCom();
            }
        }
        return vUnCom;
    }

    public String getVlrTotal() {
        List<Det> detalhes = getInfNFe().getDet();
        String vProd = "";
        if (detalhes != null && !detalhes.isEmpty()) {
            for (Det det : detalhes) {
                vProd = det.getProd().getVProd();
            }
        }
        return vProd;
    }

    public String getBCICMS() {
        String vBC = "";
        Imposto imposto = detalhe.getImposto();
        if (imposto.getICMS().getICMS00() != null) {
            vBC = imposto.getICMS().getICMS00().getVBC();
        } else if (imposto.getICMS().getICMS20() != null) {
            vBC = imposto.getICMS().getICMS20().getVBC();
        } else if (imposto.getICMS().getICMS51() != null) {
            vBC = imposto.getICMS().getICMS51().getVBC();
        } else if (imposto.getICMS().getICMS70() != null) {
            vBC = imposto.getICMS().getICMS70().getVBC();
        } else if (imposto.getICMS().getICMS90() != null) {
            vBC = imposto.getICMS().getICMS90().getVBC();
        }
        return vBC;
    }

    public String getVlrICMS() {
        List<Det> detalhes = getInfNFe().getDet();
        String vICMS = "";
        if (detalhes != null && !detalhes.isEmpty()) {
            for (Det det : detalhes) {
                Imposto imposto = det.getImposto();
                if (imposto.getICMS().getICMS00() != null) {
                    vICMS = imposto.getICMS().getICMS00().getVICMS();
                }
                if (imposto.getICMS().getICMS20() != null) {
                    vICMS = imposto.getICMS().getICMS20().getVICMS();
                }
                if (imposto.getICMS().getICMS51() != null) {
                    vICMS = imposto.getICMS().getICMS51().getVICMS();
                }
                if (imposto.getICMS().getICMS70() != null) {
                    vICMS = imposto.getICMS().getICMS70().getVICMS();
                }
                if (imposto.getICMS().getICMS90() != null) {
                    vICMS = imposto.getICMS().getICMS90().getVICMS();
                }
            }
        }
        return vICMS;
    }

    public String getVlrIPI() {
        String vIPI = "";
        if (getInfNFe() != null && getInfNFe().getDet() != null) {
            List<Det> detalhes = getInfNFe().getDet();
            if (!detalhes.isEmpty()) {
                boolean first = true;
                for (Det det : detalhes) {
                    Imposto imposto = det.getImposto();
                    if (imposto!=null && 
                            imposto.getIPI() != null && 
                            imposto.getIPI().getIPITrib() != null && 
                            imposto.getIPI().getIPITrib().getVIPI() != null && 
                            "".equals(imposto.getIPI().getIPITrib().getVIPI().trim())) {
                        if (first) {
                            first = false;
                        } else {
                            vIPI += ",";
                        }
                        vIPI += imposto.getIPI().getIPITrib().getVIPI();
                    }
                }
            }
        }
        return vIPI;
    }

    public String getVlrAliqICMS() {
        String pICMS = "";
        if (detalhe != null) {
            Imposto imposto = detalhe.getImposto();
            if (imposto != null
                    && imposto.getICMS() != null) {
                if (imposto.getICMS().getICMS00() != null) {
                    pICMS = imposto.getICMS().getICMS00().getPICMS();
                }
                if (imposto.getICMS().getICMS20() != null) {
                    pICMS = imposto.getICMS().getICMS20().getPICMS();
                }
                if (imposto.getICMS().getICMS51() != null) {
                    pICMS = imposto.getICMS().getICMS51().getPICMS();
                }
                if (imposto.getICMS().getICMS70() != null) {
                    pICMS = imposto.getICMS().getICMS70().getPICMS();
                }
                if (imposto.getICMS().getICMS90() != null) {
                    pICMS = imposto.getICMS().getICMS90().getPICMS();
                }
            }
        }
        return pICMS;
    }

    public String getAliqIPI() {
        String pIPI = "";
        if (detalhe != null) {
            Imposto imposto = detalhe.getImposto();
            if (imposto.getIPI() != null
                    && imposto.getIPI().getIPITrib() != null
                    && imposto.getIPI().getIPITrib().getPIPI() != null
                    && "".equals(imposto.getIPI().getIPITrib().getPIPI())) {
                pIPI = imposto.getIPI().getIPITrib().getPIPI();
            }
        }
        return pIPI;
    }

    public String getInscricaoMunicipal() {
        if (getInfNFe().getEmit().getIM() == null || getInfNFe().getEmit() == null) {
            return "";
        } else {
            return getInfNFe().getEmit().getIM();
        }
    }

    public String getValorTotalServicos() {
        if (getInfNFe().getTotal() == null || getInfNFe().getTotal().getISSQNtot() == null || getInfNFe().getTotal().getISSQNtot().getVServ() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getISSQNtot().getVServ();
        }
    }

    public String getBaseCalculoISSQN() {
        if (getInfNFe().getTotal() == null || getInfNFe().getTotal().getISSQNtot() == null || getInfNFe().getTotal().getISSQNtot().getVBC() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getISSQNtot().getVBC();
        }
    }

    public String getValorISSQN() {
        if (getInfNFe().getTotal() == null || getInfNFe().getTotal().getISSQNtot() == null ||  getInfNFe().getTotal().getISSQNtot().getVISS() == null) {
            return "";
        } else {
            return getInfNFe().getTotal().getISSQNtot().getVISS();
        }
    }

    public String getInformacoes() {
        if (getInfNFe().getInfAdic().getInfCpl() == null) {
            return "";
        } else {
            return getInfNFe().getInfAdic().getInfCpl();
        }
    }

    public String getReservadoAoFisco() {
        if (getInfNFe().getInfAdic().getInfAdFisco() == null) {
            return "";
        } else {
            return getInfNFe().getInfAdic().getInfAdFisco();
        }
    }

    public String getChaveAcesso() {
        if (atual.getProtNFe().getInfProt().getChNFe() == null) {
            return "";
        } else {
            return atual.getProtNFe().getInfProt().getChNFe();
        }
    }

    public String getCodigodeBarras() {
        if (atual.getProtNFe().getInfProt().getChNFe() == null) {
            return "";
        } else {
            return getChaveAcesso();
        }
    }

    public String getEntradaSaida() {
        if (getIde().getTpEmis() == null) {
            return "";
        } else {
            return getIde().getTpEmis();
        }
    }

    public String getHoraEntradaSaida() {
        if (getIde().getHSaiEnt() == null) {
            return "";
        } else {
            return getIde().getHSaiEnt();
        }
    }

    private Ide getIde() {
        return getInfNFe().getIde();
    }

    private InfNFe getInfNFe() {
        if (atual == null) {
            throw new IllegalStateException("Nota atual indefinida!");
        }
        if (atual.getNFe() == null) {
            throw new IllegalStateException("Nota atual indefinida - processamento sem conteudo!");
        }
        return atual.getNFe().getInfNFe();
    }

    private void setDetalhe(Det det) {
        this.detalhe = det;
    }
}
