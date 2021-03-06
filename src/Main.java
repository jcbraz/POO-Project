import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        MainMethods mainMethods = new MainMethods();
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String input = new String();
        boolean opcao_sair = false;
        int opcao, cF = 1;

        System.out.println("Bem Vindo ao Smart Home System!\n\n");

        do {
            do {
                System.out.println(
                        "Selecione uma das seguintes opções:\n" +
                                "1 - Criar um novo Fornecedor,\n" +
                                "2 - Criar uma nova Casa Inteligente,\n" +
                                "3 - Adicionar um novo dispositivo a Casa Inteligente,\n" +
                                "4 - Remover um dispositivo de uma Casa Inteligente,\n" +
                                "5 - Remover uma Casa Inteligente,\n" +
                                "6 - Alterar configuração de dispositivos,\n" +
                                "7 - Alterar o Fornecedor de uma dada Casa Inteligente,\n" +
                                "8 - Avançar para determinada data & Gerar Fatura.\n" +
                                "9 - Consultar estatísticas de consumo,\n" +
                                "10 - Salvar Estado do Programa\n" +
                                "11 - Carregar um Estado de Programa\n");
                try {
                    while (!sc.hasNextInt()) {
                        sc.next();
                    }
                    opcao = sc.nextInt();
                } catch (Exception e) {
                    System.out.println(
                            "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                    opcao = 0;
                }
            } while (opcao < 1 || opcao > 11);
            switch (opcao) {
                case 1:
                    System.out.println("Insira o nome do fornecedor: \n");
                    String nome = sc.next();
                    mainMethods.criaFornecedor(nome);
                    System.out.println("Fornecedor criado com sucesso! O código é: " + cF);
                    cF++;
                    break;

                case 2:
                    System.out.println(
                            "Forneça os dados necessários para proceder à criação da casa inteligente:\n");
                    System.out.println(
                            "Nome do proprietário: \n");
                    sc.nextLine();
                    String nomeProprietario = sc.nextLine();
                    System.out.println(
                            "NIF do proprietário: \n");
                    int nifProprietario = sc.nextInt();
                    System.out.println(
                            "Morada da casa: \n");
                    sc.nextLine();
                    String morada = sc.nextLine();
                    System.out.println(
                            "Qual o código do fornecedor da casa?\n" + "Os fornecedores disponíveis até ao momentos são"
                                    + mainMethods.getArt().getFornecedores());

                    int codigoFornecedor = sc.nextInt();
                    System.out.println();
                    if (!mainMethods.existeFornecedor(codigoFornecedor)) {
                        System.out.println(
                                "Fornecedor não existe. Por favor, crie o fornecedor primeiro.\n");
                    } else {
                        mainMethods.criaCasa(nomeProprietario, nifProprietario, morada, codigoFornecedor);
                        try {
                            System.out.println("Casa criada com sucesso!\n");
                            int codigoCasa = mainMethods.addCasaToFornecedor(codigoFornecedor);
                            mainMethods.criaDivisao(codigoCasa, "Sala", new HashMap<Integer, SmartDevices>());
                            mainMethods.criaDivisao(codigoCasa, "Quarto", new HashMap<Integer, SmartDevices>());
                            mainMethods.criaDivisao(codigoCasa, "Cozinha", new HashMap<Integer, SmartDevices>());
                            mainMethods.criaDivisao(codigoCasa, "Casa de Banho", new HashMap<Integer, SmartDevices>());
                        } catch (Exception e) {
                            System.out.println(
                                    e.getMessage());
                        }
                    }
                    break;

                case 3:
                    System.out.println("Insira o código da casa: \n" + "As casas disponíveis até ao momentos são"
                            + mainMethods.getArt().getCasas());
                    int codigoCasa = sc.nextInt();
                    if (!mainMethods.existeCasa(codigoCasa)) {
                        System.out.println(
                                "Casa não existe. Por favor, crie a casa primeiro.\n");
                        break;
                    } else {
                        System.out.println("Insira a divisão que pretende selecionar/criar.\n"
                                + "As divisões disponíveis até ao momentos são: \n"
                                + mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes());
                        sc.nextLine();
                        String divisao = sc.nextLine();
                        int resposta = 0;
                        if (!mainMethods.existeDivisaoInCasa(divisao)) {
                            do {
                                System.out.println(
                                        "Divisão não existe. Pretende adicionar a divisão que acabou de mencionar à casa?\n"
                                                + "1 - Sim\n" + "2 - Não\n");
                                try {
                                    resposta = sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println(
                                            "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                    resposta = 0;
                                }
                            } while (resposta < 1 || resposta > 2);
                            if (resposta == 1) {
                                mainMethods.criaDivisao(codigoCasa, divisao, new HashMap<Integer, SmartDevices>());
                                System.out.println("Divisão criada com sucesso!");
                            } else
                                System.out.println("Divisão não adicionada.\n");
                        } else {
                            int tipo = 0;
                            float consumo = 0;
                            do {
                                System.out.println(
                                        "Qual o tipo de dispositivo?\n" + "1 - Lampada(Bulb)\n" + "2 - Speaker\n"
                                                + "3 - Camera\n");
                                try {
                                    tipo = sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println(
                                            "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                    tipo = 0;
                                }
                            } while (tipo < 1 || tipo > 3);
                            if (tipo == 1) {
                                System.out.println("Insira os dados para a criação da lampada:\n");
                                int tonalidadeN = 0;
                                String tonalidade = new String();
                                do {
                                    System.out.println("Qual o tipo de dispositivo?\n" + "1 - Warm\n" + "2 - Neutral\n"
                                            + "3 - Cold\n");
                                    try {
                                        tonalidadeN = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        tonalidadeN = 0;
                                    }

                                } while (tonalidadeN < 1 || tonalidadeN > 3);
                                if (tonalidadeN == 1)
                                    tonalidade = "Warm";
                                else if (tonalidadeN == 2)
                                    tonalidade = "Neutral";
                                else
                                    tonalidade = "Cold";
                                System.out.println("Insira o custo de instalação da Bulb.\n");
                                float custoInstalacaoBulb = sc.nextFloat();
                                System.out.println("Insira as dimensões (em cms)\n");
                                double dimensao = sc.nextDouble();
                                System.out.println(
                                        "Insira o consumo diário do dispositivo (em KWh (com vírgula em caso de número decimal.))\n");
                                consumo = sc.nextFloat();
                                int ligadoB = 0;
                                do {
                                    System.out.println(
                                            "\nDeseja ligar o dispositivo mal este seja adicionado à casa?\n"
                                                    + "1 - Sim\n" + "2 - Não\n");
                                    try {
                                        ligadoB = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        tipo = 0;
                                    }
                                } while (ligadoB < 1 || ligadoB > 2);
                                if (ligadoB == 1)
                                    mainMethods.criaBulb(true, custoInstalacaoBulb, tonalidade, dimensao, consumo,
                                            codigoCasa, divisao);
                                else
                                    mainMethods.criaBulb(false, custoInstalacaoBulb, tonalidade, dimensao, consumo,
                                            codigoCasa, divisao);
                            } else if (tipo == 2) {
                                System.out.println("Insira os dados para a criação do speaker:\n");
                                System.out.println("Insira o custo de instalação do Speaker.\n");
                                float custoInstalacaoSpeaker = sc.nextFloat();
                                System.out.println("Insira o volume inicial\n");
                                int volume = sc.nextInt();
                                System.out.println("Insira o nome da Rádio\n");
                                String nomeRadio = sc.next();
                                System.out.println("Insira a marca do dispositivo\n");
                                String marca = sc.next();
                                System.out.println(
                                        "Insira o consumo diário do dispositivo (em KWh (com vírgula em caso de número decimal.))\n");
                                consumo = sc.nextFloat();
                                int ligadoS = 0;
                                do {
                                    System.out.println(
                                            "\nDeseja ligar o dispositivo mal este seja adicionado à casa?\n"
                                                    + "1 - Sim\n" + "2 - Não\n");
                                    try {
                                        ligadoS = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        tipo = 0;
                                    }
                                } while (ligadoS < 1 || ligadoS > 2);
                                if (ligadoS == 1)
                                    mainMethods.criaSpeaker(true, custoInstalacaoSpeaker, volume, nomeRadio, marca,
                                            consumo, codigoCasa, divisao);
                                else
                                    mainMethods.criaSpeaker(false, custoInstalacaoSpeaker, volume, nomeRadio, marca,
                                            consumo, codigoCasa, divisao);
                            } else {
                                System.out.println("Insira os dados para a criação da camera:\n");
                                System.out.println("Insira o custo de instalação da Camera.\n");
                                float custoInstalacaoCamera = sc.nextFloat();
                                int opcaoCameraResolucao = 0, x = 0, y = 0;
                                do {
                                    System.out.println("Indique a resolução que pretende\n" +
                                            "1 - 640 x 480\n" +
                                            "2 - 1024 x 768\n" +
                                            "3 - 1280 x 768\n" +
                                            "4 - 1366 x 768\n" +
                                            "5 - 1920 x 1080\n" +
                                            "6 - 2160 x 1440\n" +
                                            "7 - 3840 x 2160\n");
                                    try {
                                        opcaoCameraResolucao = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoCameraResolucao = 0;
                                    }
                                } while (opcaoCameraResolucao < 1 || opcaoCameraResolucao > 7);
                                if (opcao == 1) {
                                    x = 640;
                                    y = 480;
                                } else if (opcao == 2) {
                                    x = 1024;
                                    y = 768;
                                } else if (opcao == 3) {
                                    x = 1280;
                                    y = 768;
                                } else if (opcao == 4) {
                                    x = 1366;
                                    y = 768;
                                } else if (opcao == 5) {
                                    x = 1920;
                                    y = 1080;
                                } else if (opcao == 6) {
                                    x = 2160;
                                    y = 1440;
                                } else if (opcao == 7) {
                                    x = 3840;
                                    y = 2160;
                                }
                                System.out.println("Insira o tamanho de ficheiro (em segundos)\n");
                                double filesize = sc.nextDouble();
                                System.out.println(
                                        "Insira o consumo diário do dispositivo (em KWh (com vírgula em caso de número decimal.))\n");
                                consumo = sc.nextFloat();
                                int ligadoC = 0;
                                do {
                                    System.out.println(
                                            "\nDeseja ligar o dispositivo mal este seja adicionado à casa?\n"
                                                    + "1 - Sim\n" + "2 - Não\n");
                                    try {
                                        ligadoC = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        tipo = 0;
                                    }
                                } while (ligadoC < 1 || ligadoC > 2);
                                if (ligadoC == 1)
                                    mainMethods.criaCamera(true, custoInstalacaoCamera, x, y, filesize, consumo,
                                            codigoCasa, divisao);
                                else
                                    mainMethods.criaCamera(false, custoInstalacaoCamera, x, y, filesize, consumo,
                                            codigoCasa, divisao);
                            }
                        }
                        break;
                    }
                case 4:
                    System.out.println(
                            "Insira o código da casa onde o dispositivo se encontra. As casas disponíveis até ao momento são: \n"
                                    + mainMethods.getArt().getCasas());
                    int codigoCasaElemDisp = sc.nextInt();
                    if (!mainMethods.existeCasa(codigoCasaElemDisp)) {
                        System.out.println("Casa não existe.\n");
                    } else {
                        System.out.println(
                                "Qual é a divisão onde o dispositivo se encontra? As divisões disponíveis são: \n"
                                        + mainMethods.getArt().getCasas().get(codigoCasaElemDisp).getDivisoes());
                        sc.nextLine();
                        String divisaoDisp = sc.nextLine();
                        if (!mainMethods.existeDivisaoInCasa(divisaoDisp)) {
                            System.out.println("Divisão não existe.\n");
                        } else {
                            System.out.println("Qual o código do dispositivo? Os dispositivos presente são: \n"
                                    + mainMethods.getArt().getCasas().get(codigoCasaElemDisp).getDivisoes()
                                            .get(divisaoDisp).values());
                            int codigoDispositivoInDiv = sc.nextInt();
                            if (!mainMethods.getArt().getCasas().get(codigoCasaElemDisp).getDivisoes().get(divisaoDisp)
                                    .containsKey(codigoDispositivoInDiv)) {
                                System.out.println("Dispositivo não existe.\n");
                            } else {
                                try {
                                    mainMethods.addPedido(pedido -> {
                                        pedido.getArt().getCasas().get(codigoCasaElemDisp).getDivisoes()
                                                .get(divisaoDisp)
                                                .remove(codigoDispositivoInDiv);
                                    });
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        System.out.println("Dispositivo removido com sucesso!\n");
                    }
                    break;
                case 5:
                    System.out.println("Insira o código da casa: \n" + "As casas disponíveis até ao momentos são"
                            + mainMethods.getArt().getCasas());
                    codigoCasa = sc.nextInt();
                    if (!mainMethods.existeCasa(codigoCasa)) {
                        System.out.println(
                                "Casa não existe.\n");
                    } else {
                        try {
                            mainMethods.addPedido(pedido -> {
                                pedido.removeCasaFromArt(codigoCasa);
                                pedido.getArt().getFornecedores().values().forEach(fornecedor -> {
                                    if (fornecedor.getCodigoClientes().contains(codigoCasa)) {
                                        pedido.removeCasaFromFornecedor();
                                        System.out.println(fornecedor.getCodigoClientes());
                                    }
                                });
                            });
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                    break;
                case 6:
                    System.out.println("Insira o código da casa\n" + "As casas disponíveis até ao momentos são"
                            + mainMethods.getArt().getCasas());
                    codigoCasa = sc.nextInt();
                    if (!mainMethods.existeCasa(codigoCasa)) {
                        System.out.println(
                                "Casa não existe.\n");
                    } else {
                        try {
                            System.out.println("Qual o nome da divisão que se encontra o dispositivo?\n"
                                    + "As divisões disponíveis até ao momentos são"
                                    + mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        sc.nextLine();
                        String divisao = sc.nextLine();
                        if (!mainMethods.existeDivisaoInCasa(divisao)) {
                            System.out.println("Divisão não existe. Crie uma primeiro!\n");
                        } else {
                            int opcaoDispositivos = 0;
                            do {
                                System.out.println(
                                        "Indique o tipo de dispositivo a alterar:\n" +
                                                "1 - Lâmpada\n" +
                                                "2 - Camera\n" +
                                                "3 - Speaker\n");
                                try {
                                    opcaoDispositivos = sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println(
                                            "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                    opcaoDispositivos = 0;
                                }
                            } while (opcaoDispositivos < 1 || opcaoDispositivos > 3);
                            if (opcaoDispositivos == 1) {
                                try {
                                    System.out.println(mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes()
                                            .get(divisao).values());
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                                int codigoDispositivo = 0;
                                do {
                                    System.out.println("Insira o código do dispositivo a alterar:\n");
                                    try {
                                        codigoDispositivo = sc.nextInt();
                                        mainMethods.setCodeDispositivo(codigoDispositivo);
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoDispositivos = 0;
                                    }
                                } while (!mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes().get(divisao)
                                        .containsKey(codigoDispositivo));
                                if (mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes().get(divisao)
                                        .get(codigoDispositivo).isBulb()) {
                                    int opcaoBulb = 0;
                                    do {
                                        System.out.println("O que pretende alterar?\n" +
                                                "1 - Ligar/Desligar a lâmpada\n" +
                                                "2 - Alterar a tonalidade\n" +
                                                "3 - Alterar o consumo diário\n");
                                        String estado = new String();
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            estado = "ligado";
                                        } else
                                            estado = "desligado";
                                        System.out.println("O dispositivo está " + estado + ".\n");
                                        try {
                                            opcaoBulb = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println(
                                                    "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                            opcaoBulb = 0;
                                        }
                                    } while (opcaoBulb < 1 || opcaoBulb > 3);
                                    if (opcaoBulb == 1) {
                                        if (mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes().get(divisao)
                                                .get(codigoDispositivo).isON()) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getDeviceWithCode(mainMethods.getCodeDispositivo())
                                                            .setOFF();
                                                    mainMethods.getBulbWithCode(mainMethods.getCodeDispositivo())
                                                            .setconsumoDiarioBulb(0);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getDeviceWithCode(mainMethods.getCodeDispositivo())
                                                            .setON();
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        }
                                    } else if (opcaoBulb == 2) {
                                        int opcaotonalidade = 0;
                                        do {
                                            System.out.println("Insira a nova tonalidade\n" +
                                                    "1 - Cold\n" +
                                                    "2 - Neutral\n" +
                                                    "3 - Warm\n");
                                            try {
                                                opcaotonalidade = sc.nextInt();
                                            } catch (Exception e) {
                                                System.out.println(
                                                        "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                                opcaotonalidade = 0;
                                            }
                                        } while (opcaotonalidade < 1 || opcaotonalidade > 3);
                                        if (opcaotonalidade == 1) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getBulbWithCode(mainMethods.getCodeDispositivo())
                                                            .setTonalidadeCold();
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaotonalidade == 2) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getBulbWithCode(mainMethods.getCodeDispositivo())
                                                            .setTonalidadeNeutral();
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaotonalidade == 3) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getBulbWithCode(mainMethods.getCodeDispositivo())
                                                            .setTonalidadeWarm();
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        }
                                    } else {
                                        System.out.println(
                                                "Insira o novo consumo diário(em KWh (com vírgula em caso de número decimal.))\n");
                                        float consumo = sc.nextFloat();
                                        try {
                                            mainMethods.addPedido(pedido -> {
                                                mainMethods.getBulbWithCode(mainMethods.getCodeDispositivo())
                                                        .setconsumoDiarioBulb(consumo);
                                                System.out.println("Alteração realizada com sucesso!\n");
                                            });
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } /**/else if (opcaoDispositivos == 2) { // CAMARA
                                try {
                                    System.out.println(mainMethods.getDivisao(divisao).values().toString());
                                } catch (DivisoesException e) {
                                    System.out.println(e.getMessage());
                                }
                                int codigoDispositivo = 0;
                                do {
                                    System.out.println("Insira o código do dispositivo a alterar:\n");
                                    try {
                                        codigoDispositivo = sc.nextInt();
                                        mainMethods.setCodeDispositivo(codigoDispositivo);
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoDispositivos = 0;
                                    }
                                } while (!mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes().get(divisao)
                                        .containsKey(codigoDispositivo));
                                if (mainMethods.getDeviceWithCode(codigoDispositivo).isCamera()) {
                                    int opcaoCamera = 0;
                                    do {
                                        System.out.println("O que pretende alterar?\n" +
                                                "1 - Ligar/Desligar a camera\n" +
                                                "2 - Alterar as resoluções\n" +
                                                "3 - Alterar o tamanho do ficheiro\n" +
                                                "4 - Alterar o consumo diário\n");
                                        String estado = new String();
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            estado = "ligado";
                                        } else
                                            estado = "desligado";
                                        System.out.println("O dispositivo está " + estado + ".\n");
                                        try {
                                            opcaoCamera = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println(
                                                    "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                            opcaoCamera = 0;
                                        }
                                    } while (opcaoCamera < 1 || opcaoCamera > 3);
                                    if (opcaoCamera == 1) {
                                        // Ligar ou desligar a camera
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getDeviceWithCode(mainMethods.getCodeDispositivo())
                                                            .setOFF();
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setConsumoDiarioCamera(0);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getDeviceWithCode(mainMethods.getCodeDispositivo())
                                                            .setON();
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        }
                                    } else if (opcaoCamera == 2) { // Alterar a resolução
                                        int opcaoCameraRes = 0;
                                        do {
                                            System.out.println("Indique a resolução que pretende\n" +
                                                    "1 - 640 x 480\n" +
                                                    "2 - 1024 x 768\n" +
                                                    "3 - 1280 x 768\n" +
                                                    "4 - 1366 x 768\n" +
                                                    "5 - 1920 x 1080\n" +
                                                    "6 - 2160 x 1440\n" +
                                                    "7 - 3840 x 2160\n");
                                            try {
                                                opcaoCameraRes = sc.nextInt();
                                            } catch (Exception e) {
                                                System.out.println(
                                                        "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                                opcaoCameraRes = 0;
                                            }
                                        } while (opcaoCameraRes < 1 || opcaoCameraRes > 7);
                                        if (opcaoCameraRes == 1) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(640);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(480);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaoCameraRes == 2) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(1024);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(768);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaoCameraRes == 3) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(1280);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(768);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaoCameraRes == 4) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(1366);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(768);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaoCamera == 5) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(1920);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(1080);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaoCamera == 6) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(2160);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(1440);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else if (opcaoCamera == 7) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_x(3840);
                                                    mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                            .setResolution_y(2160);
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        }
                                    } else if (opcaoCamera == 3) {
                                        System.out
                                                .println("Insira o tamanho que pretende que o ficheiro tenha em GB\n");
                                        double filesize = sc.nextDouble();
                                        try {
                                            mainMethods.addPedido(pedido -> {
                                                mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                        .setFilesize(filesize);
                                                System.out.println("Alteração realizada com sucesso!\n");
                                            });
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } else {
                                        System.out.println(
                                                "Insira o novo consumo diário (em KWh (com vírgula em caso de número decimal.))\n");
                                        float consumo = sc.nextFloat();
                                        try {
                                            mainMethods.addPedido(pedido -> {
                                                mainMethods.getCameraWithCode(mainMethods.getCodeDispositivo())
                                                        .setConsumoDiarioCamera(consumo); // consumo em KWh
                                                System.out.println("Alteração realizada com sucesso!\n");
                                                System.out.println("Alteração realizada com sucesso!\n");
                                            });
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            } /**/else if (opcaoDispositivos == 3) { // SPEAKER
                                try {
                                    System.out.println(mainMethods.getDivisao(divisao).values().toString());
                                } catch (Exception e) {
                                    System.out.println("Não existem dispositivos na divisão selecionada.\n");
                                }
                                int codigoDispositivo = 0;
                                do {
                                    System.out.println("Insira o código do dispositivo a alterar:\n");
                                    try {
                                        codigoDispositivo = sc.nextInt();
                                        mainMethods.setCodeDispositivo(codigoDispositivo);
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoDispositivos = 0;
                                    }
                                } while (!mainMethods.getArt().getCasas().get(codigoCasa).getDivisoes().get(divisao)
                                        .containsKey(codigoDispositivo));
                                if (mainMethods.getDeviceWithCode(codigoDispositivo).isSpeaker()) {
                                    int opcaoSpeaker = 0;
                                    do {
                                        System.out.println("O que pretende alterar?\n" +
                                                "1 - Ligar/Desligar o speaker\n" +
                                                "2 - Alterar a rádio que está a tocar\n" +
                                                "3 - Alterar o volume atual\n" +
                                                "4 - Alterar o consumo diário\n");
                                        String estado = new String();
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            estado = "ligado";
                                        } else
                                            estado = "desligado";
                                        System.out.println("O dispositivo está " + estado + ".\n");
                                        try {
                                            opcaoSpeaker = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println(
                                                    "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                            opcaoSpeaker = 0;
                                        }
                                    } while (opcaoSpeaker < 1 || opcaoSpeaker > 4);
                                    if (opcaoSpeaker == 1) { // Ligar ou desligar o speaker
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getDeviceWithCode(mainMethods.getCodeDispositivo())
                                                            .setOFF();
                                                    mainMethods.getSpeakerWithCode(mainMethods.getCodeDispositivo())
                                                            .setconsumoDiarioSpeaker(0);
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        } else {
                                            try {
                                                mainMethods.addPedido(pedido -> {
                                                    mainMethods.getDeviceWithCode(mainMethods.getCodeDispositivo())
                                                            .setON();
                                                    System.out.println("Alteração realizada com sucesso!\n");
                                                });
                                            } catch (Exception e) {
                                                System.out.println(e.getMessage());
                                            }
                                            System.out.println("Pedido adicionado à lista de espera");
                                        }
                                    } else if (opcaoSpeaker == 2) {
                                        System.out.println("Insira a rádio que deseja ouvir\n");
                                        String nomeRadio = sc.next();
                                        try {
                                            mainMethods.addPedido(pedido -> {
                                                mainMethods.getSpeakerWithCode(mainMethods.getCodeDispositivo())
                                                        .setNomeRadio(nomeRadio);
                                            });
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } else if (opcaoSpeaker == 3) {
                                        System.out.println("Insira o volume que deseja\n");
                                        int volume = sc.nextInt();
                                        try {
                                            mainMethods.addPedido(pedido -> {
                                                mainMethods.getSpeakerWithCode(mainMethods.getCodeDispositivo())
                                                        .setVolume(volume); // consumo em KWh
                                                System.out.println("Alteração realizada com sucesso!\n");
                                            });
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    } else {
                                        System.out.println(
                                                "Insira o novo consumo diário (em KWh (com vírgula em caso de número decimal.))\n");
                                        float consumo = sc.nextFloat();
                                        try {
                                            mainMethods.addPedido(pedido -> {
                                                mainMethods.getSpeakerWithCode(mainMethods.getCodeDispositivo())
                                                        .setconsumoDiarioSpeaker(consumo); // consumo em KWh
                                                System.out.println("Alteração realizada com sucesso!\n");
                                            });
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                    }
                                }
                            }
                        }
                    }

                    break;
                case 7:
                    System.out.println("Qual o código da casa que pretende fazer a alteração?\n"
                            + "As casas criadas até ao momento são:\n" + mainMethods.getArt().getCasas());
                    int codigoCasaToChangeFornecedor = sc.nextInt();
                    if (mainMethods.existeCasa(codigoCasaToChangeFornecedor)) {
                        System.out.println("Qual o fornecedor que pretende alterar?\n"
                                + "Os fornecedores criados até ao momento são:\n"
                                + mainMethods.getArt().getFornecedores());
                        int codigoFornecedorToChangeCasa = sc.nextInt();
                        if (mainMethods.existeFornecedor(codigoFornecedorToChangeCasa)) {
                            try {
                                mainMethods.addPedido(pedido -> {
                                    CasaInteligente ciTemp = mainMethods.getArt().getCasas()
                                            .get(codigoCasaToChangeFornecedor);
                                    int codigoAntigoFornecedor = ciTemp.getCodeFornecedor();
                                    // mainMethods.getArt().getFornecedores().get(codigoAntigoFornecedor).getClientes()
                                    // .remove(codigoCasaToChangeFornecedor);
                                    mainMethods.getArt().getFornecedores().get(codigoAntigoFornecedor)
                                            .getCodigoClientes().remove(codigoCasaToChangeFornecedor);
                                    // mainMethods.getArt().getFornecedores().get(codigoFornecedorToChangeCasa)
                                    // .addCliente(ciTemp);
                                    mainMethods.getArt().getFornecedores().get(codigoFornecedorToChangeCasa)
                                            .getCodigoClientes().add(codigoCasaToChangeFornecedor);
                                    mainMethods.getArt().getCasas().get(codigoCasaToChangeFornecedor)
                                            .setCodeFornecedor(codigoFornecedorToChangeCasa);
                                });
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            System.out.println("O fornecedor que mencionou não existe.\n");
                        }
                    } else {
                        System.out.println("A casa que mencionou não existe.\n");
                    }
                    break;
                case 8:
                    System.out.println("\nInsira a data para a qual pretende avançar (AAAA-MM-DD)\n");
                    LocalDate dateToAdvance;
                    do {
                        try {
                            dateToAdvance = LocalDate.parse(sc.next());
                        } catch (Exception e) {
                            System.out.println("\nInsira uma data valida\n");
                            dateToAdvance = LocalDate.now();
                        }
                    } while (dateToAdvance.isBefore(LocalDate.now()));
                    int dateDifference = (int) ChronoUnit.DAYS.between(LocalDate.now(), dateToAdvance);
                    Map<Integer, Double> consumoDeCasa = new HashMap<Integer, Double>();
                    mainMethods.getArt().getCasas().values()
                            .forEach(x -> consumoDeCasa.put(x.getCode(), x.energiaTotalDiariaCasa()));
                    consumoDeCasa.values().forEach(x -> x *= dateDifference);
                    System.out.println("O consumo de energia por casa é:\n" + consumoDeCasa);
                    for (Fornecedor fornece : mainMethods.getArt().getFornecedores().values()) {
                        for (int codigoCasanoFornecedor : fornece.getCodigoClientes()) {
                            CasaInteligente casadofornecedor = mainMethods.getArt().getCasas()
                                    .get(codigoCasanoFornecedor);
                            mainMethods.placeFatura(dateToAdvance, casadofornecedor.getNifProprietario(),
                                    casadofornecedor.energiaTotalDiariaCasa(),
                                    fornece.precoTotalDiarioCliente(casadofornecedor), casadofornecedor.getCode(),
                                    fornece.getCode());
                        }
                    }
                    try {
                        mainMethods.execPedidos();
                        System.out.println(
                                "Pendidos pendentes foram executados e os dados foram atualizados com sucesso.\n");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    int opcaoForStat = 0;
                    do {
                        System.out.println(
                                "Selecione o tipo de estatística a consultar:\n" +
                                        "1 - Casa com maior consumo\n" +
                                        "2 - Comercializador com maior volume de facturação\n" +
                                        "3 - Lista de facturas emitidas por um comercializador\n" +
                                        "4 - Maiores consumidores de energia\n");
                        try {
                            opcaoForStat = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println(
                                    "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                            opcaoForStat = 0;
                        }
                    } while (opcaoForStat < 1 || opcaoForStat > 4);
                    if (opcaoForStat == 1) {
                        System.out.println(
                                "A data inicial pré-definida é a data de hoje. Pretende alterar?\n" +
                                        "1 - Sim\n" +
                                        "2 - Não\n");
                        int opcaoData = sc.nextInt();
                        LocalDate dataInicio = null;
                        if (opcaoData == 1) {
                            do {
                                System.out.println(
                                        "Forneça a data inicial para a consulta de estatísticas de consumo (aaaa-mm-dd):\n");
                                String dataIniciosString = sc.next();
                                try {
                                    dataInicio = LocalDate.parse(dataIniciosString);
                                } catch (Exception e) {
                                    System.out.println(
                                            "Data inválida!\n");
                                    dataInicio = null;
                                }
                            } while (dataInicio == null);

                        } else {
                            dataInicio = LocalDate.now();
                        }
                        LocalDate dataFim = null;
                        do {

                            System.out.println(
                                    "Forneça a data final para a consulta de estatísticas de consumo (AAAA-MM-DD):\n");
                            String dataFimString = sc.next();
                            dataFim = LocalDate.parse(dataFimString);
                            if (dataFim.isBefore(dataInicio)) {
                                System.out.println("Data inválida!\n");
                                dataFim = null;
                            }
                        } while (dataFim == null);
                        System.out.println(
                                "A casa com maior consumo entre as datas selecionadas é a casa " +
                                        "com código " + mainMethods.casaComMaiorConsumoGeralemDatas() +
                                        "\n");
                    } else if (opcaoForStat == 2) {
                        System.out.println(
                                "O código do Fornecedor com maior volume de facturação segundo os dados fornecidos é:\n"
                                        + mainMethods.fornecedorComMaiorFaturacao()
                                        + "\n");
                    } else if (opcaoForStat == 3) {
                        int codigoFornecedorStat;
                        System.out.println(
                                "Forneça um código válido de fornecedor que pretende consultar:\n"
                                        + "Os fornecedores disponíveis até ao momento são "
                                        + mainMethods.getArt().getFornecedores());
                        codigoFornecedorStat = sc.nextInt();
                        boolean existeFornecedor = mainMethods.existeFornecedor(codigoFornecedorStat);
                        while (!existeFornecedor) {
                            System.out.println(
                                    "Fornecedor não existe!\n");
                            System.out.println(
                                    "Forneça um código válido de fornecedor que pretende consultar:\n");
                            codigoFornecedorStat = sc.nextInt();
                            existeFornecedor = mainMethods.existeFornecedor(codigoFornecedorStat);
                        }
                        System.out.println(mainMethods.faturasByFornecedor(1));
                        System.out.println(
                                "As facturas emitidas pelo fornecedor " + codigoFornecedorStat
                                        + " são:\n"
                                        + mainMethods.faturasByFornecedor(codigoFornecedorStat)
                                        + "\n");
                        if (mainMethods.faturasByFornecedor(codigoFornecedorStat).isEmpty())
                            System.out.println(
                                    "Ainda não foram geradas faturas. Por favor, gere as faturas antes de consultar.\n");
                        else {
                            System.out.println("Deseja consultar alguma fatura em detalhe?\n" +
                                    "1 - Sim\n" +
                                    "2 - Não\n");
                            int opcaoDetalheFatura = 0;
                            do {
                                try {
                                    opcaoDetalheFatura = sc.nextInt();
                                } catch (Exception e) {
                                    System.out.println(
                                            "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                }
                            } while (opcaoDetalheFatura < 1 || opcaoDetalheFatura > 2);
                            if (opcaoDetalheFatura == 1) {
                                System.out.println("Qual o código da casa que pretende consultar?\n"
                                        + "As casas ligadas a este fornecedor são: \n" + mainMethods.getArt()
                                                .getFornecedores().get(codigoFornecedorStat).getCodigoClientes());
                                int codigoCasaForFatura = sc.nextInt();
                                if (!mainMethods.existeCasa(codigoCasaForFatura)) {
                                    System.out.println("Casa não existe!\n");
                                } else {
                                    System.out.println(
                                            "Qual o código da fatura que pretende consultar? Os códigos de faturas estão ordenados da fatura mais antiga à mais recente. As faturas disponíveis até ao momento são: \n"
                                                    + mainMethods.printsFaturasCodes(codigoCasaForFatura));
                                    int codigoFaturaForDetalhe = sc.nextInt();
                                    if (!mainMethods.getArt().getCasas().get(codigoCasaForFatura).getCodigosDeFaturas()
                                            .contains(codigoFaturaForDetalhe)) {
                                        System.out.println("Fatura não existe!\n");
                                    } else
                                        mainMethods.printsFatura(codigoCasaForFatura);
                                }
                            }
                        }
                    } else if (opcaoForStat == 4) {
                        System.out.println("Insira a data de início do período de consulta (AAAA-MM-DD):\n");
                        String dataInicioString = sc.next();
                        LocalDate dataInicio = LocalDate.parse(dataInicioString);
                        System.out.println("Insira a data de fim do período de consulta (AAAA-MM-DD):\n");
                        String dataFimString = sc.next();
                        LocalDate dataFim = LocalDate.parse(dataFimString);
                        System.out.println("O rank de maior consumo de energia é:\n"
                                + mainMethods.rankCasasComMaiorConsumo(dataInicio, dataFim));
                    }
                    break;
                case 10:
                    System.out.println("Introduza o nome do ficheiro de armazenamento: \n");
                    String nomeFicheiro = sc.next();
                    try {
                        FileOutputStream fos = new FileOutputStream(nomeFicheiro);
                        ObjectOutputStream oos = new ObjectOutputStream(fos);
                        oos.writeObject(mainMethods);
                        oos.writeObject(mainMethods.getArt());
                        oos.writeObject(mainMethods.getArt().getCasas());
                        oos.writeObject(mainMethods.getArt().getFaturas());
                        oos.writeObject(mainMethods.getArt().getFornecedores());
                        oos.close();
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    try {
                        System.out.println("Insira o nome do ficheiro que pretende carregar: \n");
                        String nomeFicheiro2 = sc.next();
                        FileInputStream fileStream = new FileInputStream(nomeFicheiro2);
                        ObjectInputStream inputFile = new ObjectInputStream(fileStream);
                        MainMethods mainMethodsFile = (MainMethods) inputFile.readObject();
                        mainMethods.setMainMethods(mainMethodsFile);
                        System.out.println("Os dados foram adicionados com sucesso!");
                        inputFile.close();
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                    break;
            }
            System.out.println("Deseja efetuar mais alguma operação?\n" + "S/N?\n");
            input = sc.next();
            if (input.equals("N") || input.equals("n"))
                opcao_sair = true;
        } while (!opcao_sair);
        System.out.println("Obrigado por utilizar o Smart Home System.\n");
        sc.close();
        sc2.close();
    }
}