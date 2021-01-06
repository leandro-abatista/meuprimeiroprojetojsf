package br.com.cursojsf.bean;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.omnifaces.util.Messages;

import br.com.cursojsf.dao.DaoGeneric;
import br.com.cursojsf.entidades.Produto;

@ViewScoped
@ManagedBean(name = "produtoBean")
public class ProdutoBean {

	private DaoGeneric<Produto> daoGeneric = new DaoGeneric<Produto>();
	private Produto produto = new Produto();
	private List<Produto> produtos = new ArrayList<Produto>();
	
	private Part arquivoFoto;//objeto para trazer o arquivo upload
	
	public String salvar() throws IOException{
		try {
			
			/*processar imagem*/
			/*esse método salva a imagem original*/
			byte[] imagemByte = getByte(arquivoFoto.getInputStream());
			produto.setFotoIconBase64Original(imagemByte);
			
			/*transformar em um bufferimage*/
			BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imagemByte));
			
			/*pega o tipo da imagem*/
			int type = bufferedImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
			
			int largura = 200;
			int altura = 200;
			
			/*criando a miniatura*/
			BufferedImage resizedImage = new BufferedImage(largura, altura, type);
			Graphics2D graphics2d = resizedImage.createGraphics();
			graphics2d.drawImage(bufferedImage, 0, 0, largura, altura, null);
			graphics2d.dispose();
			
			/*escrever novamente a imagem em tamanho menor*/
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			String extensao = arquivoFoto.getContentType().split("\\/")[1];//retorna a extensao
			ImageIO.write(resizedImage, extensao, arrayOutputStream);
			
			String miniImagem = 
					"data:" + arquivoFoto.getContentType() + ";base64," + DatatypeConverter.printBase64Binary(arrayOutputStream.toByteArray());
			/*fim do processamento de imagem*/
			
			produto.setFotoIconBase64(miniImagem);
			produto.setExtensao(extensao);
			
			produto = daoGeneric.merge(produto);
			this.carregarListaProdutos();
			this.novo();
			Messages.addGlobalInfo("Registro salvo com sucesso!");
			return "";
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo registro!");
		}
		return "";
	}
	
	public void novo() {
		produto = new Produto();
	}
	
	public String remove() {
		try {
			
			daoGeneric.deletePorId(produto);
			this.carregarListaProdutos();
			Messages.addGlobalInfo("Registro excluído com sucesso!");
			return "";
			
		} catch (Exception e) {
			e.printStackTrace();
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar excluir o registro!");
		}
		return "";
	}
	
	@PostConstruct
	public void carregarListaProdutos() {
		produtos = daoGeneric.getListEntity(Produto.class);
	}
	
	/**
	 * Método que converte inputStream para array de byte
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	private byte[] getByte(InputStream inputStream) throws IOException {
		
		int len;//variável de controle
		int size = 1024;//tamanho do arquivo
		byte[] buf = null;
		
		if (inputStream instanceof ByteArrayInputStream) {
			
			size = inputStream.available();
			buf  = new byte[size];
			len = inputStream.read(buf, 0, size);
			
		} else {
			
			ByteArrayOutputStream bOutputStream = new ByteArrayOutputStream();//saída de midia em forma de bytes
			buf = new byte[size];
			
			while ((len = inputStream.read(buf, 0, size)) != -1) {
				bOutputStream.write(buf, 0, len);
			}
			
			buf = bOutputStream.toByteArray();
		}
		
		return buf;
	}
	
	public void download() {
		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getInitParameterMap();
		String fileDownloadId = params.get("fileDownloadId");
		System.out.println(fileDownloadId);
	}

	public DaoGeneric<Produto> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<Produto> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public Part getArquivoFoto() {
		return arquivoFoto;
	}
	
	public void setArquivoFoto(Part arquivoFoto) {
		this.arquivoFoto = arquivoFoto;
	}

}
