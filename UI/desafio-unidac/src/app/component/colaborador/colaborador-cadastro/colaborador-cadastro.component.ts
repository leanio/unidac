import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ColaboradorService } from 'src/app/service/colaborador.service';
import { ToastHandlerService } from 'src/app/shared/handler/toast-handler.service';
import { AlimentoCadastroRequest } from 'src/app/shared/model/colaborador-model';
import { ColaboradorCadastroRequest } from 'src/app/shared/model/colaborador-model';
import { objectify } from 'tslint/lib/utils';

@Component({
  selector: 'app-colaborador-cadastro',
  templateUrl: './colaborador-cadastro.component.html',
  styleUrls: ['./colaborador-cadastro.component.css']
})
export class ColaboradorCadastroComponent implements OnInit {
  colaborador = new ColaboradorCadastroRequest();
  novoAlimento = new AlimentoCadastroRequest();
  atualizacaoAlimento: any;

  constructor(
    private colaboradorService: ColaboradorService,
    private toastHandlerService: ToastHandlerService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    if (!this.isNovoColaborador()) {
      this.carregarColaborador(this.getParametroId());
    }
  }

  salvarColaborador(): void {
    this.isNovoColaborador() ? this.adicionarColaborador() : this.atualizarColaborador();
  }

  atualizarColaborador(): void {
    this.colaboradorService.atualizar(this.getParametroId(), this.colaborador).then(() => {
      this.navegarParaColaColaboradores();
      this.toastHandlerService.sucesso('Colaborador atualizado');
    }).catch(error => {
      this.toastHandlerService.erroResponse(error);
    })
  }

  adicionarColaborador(): void {
    this.colaboradorService.adicionar(this.colaborador).then(() => {
      this.navegarParaColaColaboradores();
      this.toastHandlerService.sucesso('Colaborador cadastrado');
    }).catch(error => {
      this.toastHandlerService.erroResponse(error);
    })
  }

  carregarColaborador(colaboradorId: number): void {
    this.colaboradorService.buscarPeloId(colaboradorId).then(colaborador => {
      this.colaborador = colaborador;
    }).catch(error => {
      this.navegarParaColaColaboradores();
      this.toastHandlerService.erroResponse(error);
    });
  }

  salvarAlimento(): void {
    this.validarEdicaoListaAlimentos();
    this.isNovoAlimento() ? this.adicionarAlimento() : this.editarAlimento();
  }

  adicionarAlimento(): void {
    this.colaborador.alimentos.push(this.novoAlimento);
    this.renovarAlimento();
  }

  editarAlimento(): void {
    this.atualizacaoAlimento.nome = this.novoAlimento.nome;
    this.renovarAlimento();
  }

  selecionarAlimentoAtualizacao(alimento: any): void {
    this.novoAlimento = {... alimento};
    this.atualizacaoAlimento = alimento;
  }

  removerAlimento(alimento: AlimentoCadastroRequest): void {
    this.colaborador.alimentos = this.colaborador.alimentos.filter(item => item != alimento);

    if (alimento === this.atualizacaoAlimento) {
      this.renovarAlimento();
    }
  }

  renovarAlimento(): void {
    this.atualizacaoAlimento = undefined;
    this.novoAlimento = new AlimentoCadastroRequest();
  }

  validarEdicaoListaAlimentos(): void {
    const existeAlimentoNaLista = this.colaborador.alimentos.some(obj => {
      return obj !== this.atualizacaoAlimento && obj.nome.toUpperCase() === this.novoAlimento.nome.toUpperCase();
    });

    if (existeAlimentoNaLista) {
      this.toastHandlerService.erro('Este alimento já existe na lista de alimentos');
      throw new Error();
    }
  }

  isNovoColaborador(): boolean {
    return this.getParametroId() === undefined;
  }

  isNovoAlimento(): boolean {
    return this.atualizacaoAlimento === undefined;
  }

  getParametroId(): number {
    return this.activatedRoute.snapshot.params.id;
  }

  navegarParaColaColaboradores(): void {
    this.router.navigateByUrl('/colaboradores');
  }

}
