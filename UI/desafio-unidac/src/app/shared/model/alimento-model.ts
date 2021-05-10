
export class AlimentoResponse {
    id: number;
    nome: string;
    colaborador: ColaboradorResponse;
}

export class ColaboradorResponse {
    cpf: string;
}