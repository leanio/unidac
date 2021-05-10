import { Component, OnInit } from '@angular/core';
import { AlimentoService } from 'src/app/service/alimento.service';
import { ToastHandlerService } from 'src/app/shared/handler/toast-handler.service';
import { AlimentoResponse } from 'src/app/shared/model/alimento-model';

@Component({
  selector: 'app-alimento-listagem',
  templateUrl: './alimento-listagem.component.html',
  styleUrls: ['./alimento-listagem.component.css']
})
export class AlimentoListagemComponent implements OnInit {
  alimentos: AlimentoResponse[];

  constructor(
    private alimentoService: AlimentoService,
    private toastHandlerService: ToastHandlerService
  ) { }

  ngOnInit(): void {
    this.carregarAlimentos();
  }

  carregarAlimentos() {
    this.alimentoService.buscarTodos().then(alimentos => {
      this.alimentos = alimentos;
    }).catch(() => {
      this.toastHandlerService.erro('Não foi possível carregar os alimentos');
    })
  }

}
