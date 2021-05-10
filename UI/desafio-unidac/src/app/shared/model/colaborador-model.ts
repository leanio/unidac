export class ColaboradorRequest {
    nome: string;
    cpf: string;
}

export class ColaboradorCadastroRequest extends ColaboradorRequest {
    alimentos: AlimentoCadastroRequest[];

    constructor() {
        super();
        this.alimentos = [];
    }
}

export class ColaboradorResponse {
    id: number;
    nome: string;
    cpf: string
    alimentos: AlimentoResponse[]
}

export class AlimentoCadastroRequest {
    nome: string;
}

export class AlimentoResponse {
    id: number;
    nome: string;
}