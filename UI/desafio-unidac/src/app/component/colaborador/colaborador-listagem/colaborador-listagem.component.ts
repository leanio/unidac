import { Component, OnInit } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { ColaboradorService } from 'src/app/service/colaborador.service';
import { ToastHandlerService } from 'src/app/shared/handler/toast-handler.service';
import { ColaboradorResponse } from 'src/app/shared/model/colaborador-model';

@Component({
  selector: 'app-colaborador-listagem',
  templateUrl: './colaborador-listagem.component.html',
  styleUrls: ['./colaborador-listagem.component.css']
})
export class ColaboradorListagemComponent implements OnInit {
  colaboradores: ColaboradorResponse[];

  constructor(
    private colaboradorService: ColaboradorService,
    private toastHandlerService: ToastHandlerService,
    private confirmationService: ConfirmationService
  ) { }

  ngOnInit(): void {
    this.carregarColaboradores();
  }

  remover(id: number): void {
    this.colaboradorService.remover(id).then(() => {
      this.carregarColaboradores();
      this.toastHandlerService.sucesso('Colaborador removido');
    }).catch(error => {
      this.toastHandlerService.erroResponse(error);
    });
  }

  carregarColaboradores(): void {
    this.colaboradorService.buscarTodos().then(colaboradores => {
      this.colaboradores = colaboradores;
    }).catch(() => {
      this.toastHandlerService.erro('Não foi possível carregar os colaboradores');
    });
  }

  confirmarRemocao(id: number, event: Event): void {
    this.confirmationService.confirm({
        target: event.target,
        message: 'Você tem certeza que deseja remover?',
        icon: 'pi pi-exclamation-triangle',
        acceptLabel: 'Sim',
        rejectLabel: 'Não',
        accept: () => {
            this.remover(id);
        }
    });
}

}
