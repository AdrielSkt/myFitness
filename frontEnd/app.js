function consultarPessoa() {
    const cpf = document.getElementById('cpf-consultar').value;

    document.getElementById('resultado').textContent = "";
    
    fetch(`http://localhost:8080/pessoa/${cpf}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Pessoa não encontrada');
            }
            return response.json();
        })
        .then(data => {
       
            const resultado = `Nome: ${data.nome}, Data de Nascimento: ${data.datNascimento}, Sexo: ${data.sexo}, Altura: ${data.altura} cm, Peso: ${data.peso} kg`;
            
            document.getElementById('edicao-pessoa').style.display = 'block';
            document.getElementById('nome-editar').value = data.nome;
            document.getElementById('datNascimento-editar').value = data.datNascimento;
            document.getElementById('sexo-editar').value = data.sexo;
            document.getElementById('altura-editar').value = data.altura;
            document.getElementById('peso-editar').value = data.peso;
        })
        .catch(error => {
            console.error('Erro:', error);
            document.getElementById('resultado').textContent = 'Erro ao consultar pessoa';
        });
}

function salvarEdicoes() {
    const cpf = document.getElementById('cpf-consultar').value;
    const nome = document.getElementById('nome-editar').value;
    const datNascimento = document.getElementById('datNascimento-editar').value;
    const sexo = document.getElementById('sexo-editar').value;
    const altura = document.getElementById('altura-editar').value;
    const peso = document.getElementById('peso-editar').value;

    const dadosAtualizados = {
        cpf,
        nome,
        datNascimento,
        sexo,
        altura,
        peso,
    };

    fetch(`http://localhost:8080/pessoa`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dadosAtualizados),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao salvar edições');
        }
        document.getElementById('resultado').textContent = 'Edições salvas com sucesso';
    })
    .catch(error => {
        console.error('Erro:', error);
        document.getElementById('resultado').textContent = 'Erro ao salvar edições';
    });

    limpar();
}


function excluir(){
    const cpf = document.getElementById('cpf-consultar').value;



    fetch(`http://localhost:8080/pessoa/${cpf}`, {
        method: 'DELETE',
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Falha ao deletar pessoa');
        }
        document.getElementById('resultado').textContent = 'Edições salvas com sucesso';
        limpar();
    });



}


function cadastrarPessoa() {
    const cpf = document.getElementById('cpf-cadastrar').value;
    const nome = document.getElementById('nome-cadastrar').value;
    const datNascimento = document.getElementById('datNascimento-cadastrar').value;
    const sexo = document.getElementById('sexo-cadastrar').value;
    const altura = document.getElementById('altura-cadastrar').value;
    const peso = document.getElementById('peso-cadastrar').value;

    const novaPessoa = {
        cpf,
        nome,
        datNascimento,
        sexo,
        altura,
        peso,
    };

    fetch('http://localhost:8080/pessoa', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(novaPessoa),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao cadastrar pessoa');
        }
        document.getElementById('resultado-cadastro').textContent = 'Pessoa cadastrada com sucesso';
    })
    .catch(error => {
        console.error('Erro:', error);
        document.getElementById('resultado-cadastro').textContent = 'Erro ao cadastrar pessoa';
    });
    limpar();
}

function limpar(){

    //limpa dados de edicao
    document.getElementById('edicao-pessoa').style.display = "";
    document.getElementById('nome-editar').value = "";
    document.getElementById('datNascimento-editar').value = "";
    document.getElementById('sexo-editar').value = "";
    document.getElementById('altura-editar').value = "";
    document.getElementById('peso-editar').value = "";
    document.getElementById('edicao-pessoa').style.display = 'none';

    //limpa dados de cadastro
    document.getElementById('cpf-cadastrar').value = "";
    document.getElementById('nome-cadastrar').value = "";
    document.getElementById('datNascimento-cadastrar').value = "";
    document.getElementById('sexo-cadastrar').value = "";
    document.getElementById('altura-cadastrar').value = "";
    document.getElementById('peso-cadastrar').value = "";

    document.getElementById('resultado').textContent = "";

}

function calculaPesoIdeal(){
    const cpf = document.getElementById('cpf-consultar').value;
    const nome = document.getElementById('nome-editar').value;
    const datNascimento = document.getElementById('datNascimento-editar').value;
    const sexo = document.getElementById('sexo-editar').value;
    const altura = document.getElementById('altura-editar').value;
    const peso = document.getElementById('peso-editar').value;

    const dadosCalculo = {
        cpf,
        nome,
        datNascimento,
        sexo,
        altura,
        peso,
    };

    fetch(`http://localhost:8080/pessoa/calcula_peso_ideal`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(dadosCalculo),
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Erro ao realizar calculo');
        }
        return response.json();
    })
    .then(data => {
         const resultado = `O peso ideal para voce, e de ${data.toFixed(2)} kg`;
        document.getElementById('resultado').textContent = resultado;
    })
    .catch(error => {
        console.error('Erro:', error);
        document.getElementById('resultado').textContent = 'Erro ao realizar calculo';
    });

    limpar();

}



document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('consultar').addEventListener('click', consultarPessoa);
    document.getElementById('salvar-editar').addEventListener('click', salvarEdicoes);
    document.getElementById('excluir').addEventListener('click', excluir);
    document.getElementById('cadastrar').addEventListener('click', cadastrarPessoa);
    document.getElementById('limpar').addEventListener('click', limpar);
    document.getElementById('calcula-peso-ideal').addEventListener('click', calculaPesoIdeal);
});