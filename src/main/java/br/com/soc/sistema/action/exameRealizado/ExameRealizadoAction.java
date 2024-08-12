package br.com.soc.sistema.action.exameRealizado;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.soc.sistema.business.ExameRealizadoBusiness;
import br.com.soc.sistema.infra.Action;
import br.com.soc.sistema.relatorioApi.BaixarRelatorio;
import br.com.soc.sistema.vo.ExameRealizadoVo;

public class ExameRealizadoAction extends Action {
    private ExameRealizadoBusiness business = new ExameRealizadoBusiness();
    private BaixarRelatorio baixarRelatorioExcel = new BaixarRelatorio();

    private List<ExameRealizadoVo> examesRealizados = new ArrayList<>();
    private ExameRealizadoVo exameRealizadoVo = new ExameRealizadoVo();
    private String dataExame;
    private String DataInicio;
    private String DataFim;
    private String mensagemErro = "";

    public String filtrar() {
        try {
            this.examesRealizados = business.filtrarPorDatas(DataInicio, DataFim);
        } catch (Exception e) {
            return REDIRECT;
        }
        return SUCCESS;
    }

    public String todos() {
        try {
            this.examesRealizados.addAll(business.obterExamesRealizados());
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }

    public String novo() {
        if (this.exameRealizadoVo.getExameVo().getIdExame() == null || this.exameRealizadoVo.getFuncionarioVo().getIdFuncionario() == null) {
        	
            return INPUT;
        }
        try {
            business.salvarExameRealizado(exameRealizadoVo);
        } catch (Exception e) {
            mensagemErro = e.getMessage();
            return INPUT;
        }
        return REDIRECT;
    }
    
    public String editar() {
        if (exameRealizadoVo.getIdExameRealizado() == null) {
            return ERROR;
        }
        try {
            exameRealizadoVo = business.obterExameRealizadoPorId(exameRealizadoVo.getIdExameRealizado());
        } catch (Exception e) {
            mensagemErro = e.getMessage();
            return ERROR;
        }
        return EDITAR;
    }

    public String salvarEdicao() {
        try {
            business.editarExameRealizado(exameRealizadoVo);
        } catch (Exception e) {
            mensagemErro = e.getMessage();
            return EDITAR;
        }
        return REDIRECT;
    }

    public String excluir() {
        try {
            business.excluirExameRealizado(exameRealizadoVo);
        } catch (Exception e) {
            return REDIRECT;
        }
        return REDIRECT;
    }

    public String baixarRelatorio() {
        try {
            this.examesRealizados = business.filtrarPorDatas(DataInicio, DataFim);
            if (this.examesRealizados.isEmpty()) {
                mensagemErro = "Nenhum registro encontrado";
                return SUCCESS;
            }
            baixarRelatorioExcel.baixar(examesRealizados);
            mensagemErro = "Dowload efetuado com sucesso";
        } catch (Exception e) {
            mensagemErro = "Não foi possível baixar o relatório";
            return SUCCESS;
        }
        return SUCCESS;
    }



    public String getDataExame() {
        return dataExame;
    }

    public void setDataExame(String dataExame) throws ParseException {
        this.dataExame = dataExame;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        exameRealizadoVo.setDataExame(formatter.parse(this.dataExame));
    }

    public ExameRealizadoVo getExameRealizadoVo() {
        return exameRealizadoVo;
    }

    public void setExameRealizadoVo(ExameRealizadoVo exameRealizadoVo) {
        this.exameRealizadoVo = exameRealizadoVo;
    }

    public List<ExameRealizadoVo> getExamesRealizados() {
        return examesRealizados;
    }

    public void setExamesRealizados(List<ExameRealizadoVo> examesRealizados) {
        this.examesRealizados = examesRealizados;
    }

    public String getDataInicio() {
        return DataInicio;
    }

    public void setDataInicio(String DataInicio) {
        this.DataInicio = DataInicio;
    }

    public String getDataFim() {
        return DataFim;
    }

    public void setDataFim(String DataFim) {
        this.DataFim = DataFim;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}