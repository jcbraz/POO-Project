import java.util.Scanner;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.time.temporal.ChronoUnit;

public class Main {

    // FICA A FALTAR:
    // CRIAR UM MÉTODO QUE IMPRIME UMA FATURA AO UTILIZADOR (MOSTAR DE UMA FORMA
    // APRESENTÁVEL OS DADOS DA CASA, ETC.)
    public static void main(String[] args) {
        MainMethods mainMethods = new MainMethods();
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String input = new String();
        boolean opcao_sair = false;
        int opcao;

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
                                "9 - Consultar estatísticas de consumo,\n"
                                );
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
            } while (opcao < 1 || opcao > 9);
            switch (opcao) {
                case 1:
                    System.out.println("Insira o nome do fornecedor: \n");
                    String nome = sc.next();
                    mainMethods.criaFornecedor(nome);
                    System.out.println("Fornecedor criado com sucesso! O código é: " + mainMethods.getCodeFornecedor());
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
                    mainMethods.criaCasa(nomeProprietario, nifProprietario, morada);
                    System.out.println(
                            "Qual o código do fornecedor da casa?\n" + "Os fornecedores disponíveis até ao momentos são"
                                    + mainMethods.getArt().getFornecedores());

                    int codigoFornecedor = sc.nextInt();
                    System.out.println();
                    if (!mainMethods.existeFornecedoremArticulador(codigoFornecedor)) {
                        System.out.println(
                                "Fornecedor não existe. Por favor, crie o fornecedor primeiro.\n");
                    } else {
                        System.out.println("Casa criada com sucesso!\n");
                        mainMethods.addCasaToFornecedor();
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
                        mainMethods.criaDivisao("Sala", new HashMap<Integer, SmartDevices>());
                        mainMethods.criaDivisao("Quarto", new HashMap<Integer, SmartDevices>());
                        mainMethods.criaDivisao("Cozinha", new HashMap<Integer, SmartDevices>());
                        mainMethods.criaDivisao("Casa de Banho", new HashMap<Integer, SmartDevices>());
                        System.out.println("Insira a divisão que pretende selecionar/criar.\n"
                                + "As divisões disponíveis até ao momentos são: \n"
                                + mainMethods.getCiWithCode(codigoCasa).getDivisoes());
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
                                mainMethods.criaDivisao(divisao, new HashMap<Integer, SmartDevices>());
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
                                System.out.println(": Insira a tonalidade (Cold, Neutral ou Warm)\n");
                                String tonalidade = sc.next();
                                System.out.println("Insira as dimensões\n");
                                double dimensao = sc.nextDouble();
                                System.out.println(
                                        "Insira o consumo diário do dispositivo (em KWh (com vírgula em caso de número decimal.))\n");
                                consumo = sc.nextFloat();
                                mainMethods.criaBulb(false, tonalidade, dimensao, consumo, divisao);
                            } else if (tipo == 2) {
                                System.out.println("Insira os dados para a criação do speaker:\n");
                                System.out.println(": Insira o volume inicial\n");
                                int volume = sc.nextInt();
                                System.out.println("Insira o nome da Rádio\n");
                                String nomeRadio = sc.next();
                                System.out.println("Insira a marca do dispositivo\n");
                                String marca = sc.next();
                                System.out.println(
                                        "Insira o consumo diário do dispositivo (em KWh (com vírgula em caso de número decimal.))\n");
                                consumo = sc.nextFloat();
                                mainMethods.criaSpeaker(false, volume, nomeRadio, marca, consumo, divisao);
                            } else {
                                System.out.println("Insira os dados para a criação da camera:\n");
                                int opcaoCameraResolucao = 0, x = 0, y = 0;
                                do {
                                    System.out.println("Indique a resolução que pretende\n" +
                                            "1 - 640 x 480\n" +
                                            "2 - 1280 x 720\n" +
                                            "3 - 1920 x 1080\n" +
                                            "4 - 3840 x 2160\n");
                                    try {
                                        opcaoCameraResolucao = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoCameraResolucao = 0;
                                    }
                                } while (opcaoCameraResolucao < 1 || opcaoCameraResolucao > 4);
                                if (opcao == 1) {
                                    x = 640;
                                    y = 480;
                                } else if (opcao == 2) {
                                    x = 1280;
                                    y = 720;
                                } else if (opcao == 3) {
                                    x = 1920;
                                    y = 1080;
                                } else if (opcao == 4) {
                                    x = 3840;
                                    y = 2160;
                                }
                                System.out.println("Insira o tamanho de ficheiro (em segundos)\n");
                                double filesize = sc.nextDouble();
                                System.out.println(
                                        "Insira o consumo diário do dispositivo (em KWh (com vírgula em caso de número decimal.))\n");
                                consumo = sc.nextFloat();
                                mainMethods.criaCamera(false, x, y, filesize, consumo, divisao);
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
                                mainMethods.getArt().getCasas().get(codigoCasaElemDisp).getDivisoes().get(divisaoDisp)
                                        .remove(codigoDispositivoInDiv);
                            }
                        }
                    }
                    System.out.println("Dispositivo removido com sucesso!\n");

                    break;
                case 5:
                    System.out.println("Insira o código da casa: \n" + "As casas disponíveis até ao momentos são"
                            + mainMethods.getArt().getCasas());
                    codigoCasa = sc.nextInt();
                    if (!mainMethods.existeCasa(codigoCasa)) {
                        System.out.println(
                                "Casa não existe.\n");
                    } else {
                        mainMethods.removeCasaFromArt(codigoCasa);
                        mainMethods.getArt().getFornecedores().values().forEach(fornecedor -> {
                            if (fornecedor.getClientes().containsKey(codigoCasa)) {
                                mainMethods.removeCasaFromFornecedor();
                                System.out.println(fornecedor.getClientes());
                            }
                        });
                    }
                    System.out.println("Casa removida com sucesso!\n");
                    break;
                case 6:
                    System.out.println("Insira o código da casa\n" + "As casas disponíveis até ao momentos são"
                            + mainMethods.getArt().getCasas());
                    codigoCasa = sc.nextInt();
                    if (!mainMethods.existeCasa(codigoCasa)) {
                        System.out.println(
                                "Casa não existe.\n");
                    } else {
                        System.out.println("Qual o nome da divisão que se encontra o dispositivo?\n"
                                + "As divisões disponíveis até ao momentos são"
                                + mainMethods.getCiWithCode(codigoCasa).getDivisoes());
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
                                System.out.println(mainMethods.getDivisao(divisao).values().toString());
                                int codigoDispositivo = 0;
                                do {
                                    System.out.println("Insira o código do dispositivo a alterar:\n");
                                    try {
                                        codigoDispositivo = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoDispositivos = 0;
                                    }
                                } while (!mainMethods.existeDispositivo(codigoDispositivo));

                                if (mainMethods.getDeviceWithCode(codigoDispositivo).isBulb()) {
                                    int opcaoBulb = 0;
                                    do {
                                        System.out.println("O que pretende alterar?\n" +
                                                "1 - Ligar/Desligar a lâmpada\n" +
                                                "2 - Alterar a tonalidade\n" +
                                                "3 - Alterar o consumo diário\n");
                                        try {
                                            opcaoBulb = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println(
                                                    "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                            opcaoBulb = 0;
                                        }
                                    } while (opcaoBulb < 1 || opcaoBulb > 3);
                                    if (opcaoBulb == 1) {
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            mainMethods.getDeviceWithCode(codigoDispositivo).setOFF();
                                            mainMethods.getBulbWithCode(codigoDispositivo).setconsumoDiarioBulb(0);
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        } else {
                                            mainMethods.getDeviceWithCode(codigoDispositivo).setON();
                                            System.out.println("Alteração realizada com sucesso!\n");

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
                                            mainMethods.getBulbWithCode(codigoDispositivo).setTonalidadeCold();
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        } else if (opcaotonalidade == 2) {
                                            mainMethods.getBulbWithCode(codigoDispositivo).setTonalidadeNeutral();
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        } else if (opcaotonalidade == 3) {
                                            mainMethods.getBulbWithCode(codigoDispositivo).setTonalidadeWarm();
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        }
                                    } else {
                                        System.out.println(
                                                "Insira o novo consumo diário(em KWh (com vírgula em caso de número decimal.))\n");
                                        float consumo = sc.nextFloat();
                                        mainMethods.getBulbWithCode(codigoDispositivo).setconsumoDiarioBulb(consumo);
                                        System.out.println("Alteração realizada com sucesso!\n");

                                    }
                                }
                            } /**/else if (opcaoDispositivos == 2) { // CAMARA
                                System.out.println(mainMethods.getDivisao(divisao).values().toString());
                                int codigoDispositivo = 0;
                                do {
                                    System.out.println("Insira o código do dispositivo a alterar:\n");
                                    try {
                                        codigoDispositivo = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoDispositivos = 0;
                                    }
                                } while (!mainMethods.existeDispositivo(codigoDispositivo));

                                if (mainMethods.getDeviceWithCode(codigoDispositivo).isCamera()) {
                                    int opcaoCamera = 0;
                                    do {
                                        System.out.println("O que pretende alterar?\n" +
                                                "1 - Ligar/Desligar a camera\n" +
                                                "2 - Alterar as resoluções\n" +
                                                "3 - Alterar o tamanho do ficheiro\n" +
                                                "4 - Alterar o consumo diário\n");
                                        try {
                                            opcaoCamera = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println(
                                                    "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                            opcaoCamera = 0;
                                        }
                                    } while (opcaoCamera < 1 || opcaoCamera > 3);
                                    if (opcaoCamera == 1) { // Ligar ou desligar a camera
                                        if (mainMethods.getDeviceWithCode(codigoDispositivo).isON()) {
                                            mainMethods.getDeviceWithCode(codigoDispositivo).setOFF();
                                            mainMethods.getCameraWithCode(codigoDispositivo).setConsumoDiarioCamera(0);
                                            System.out.println("Alteração realizada com sucesso!\n");
                                        } else {
                                            mainMethods.getDeviceWithCode(codigoDispositivo).setON();
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        }
                                    } else if (opcaoCamera == 2) { // Alterar a resolução
                                        int opcaoCameraRes = 0;
                                        do {
                                            System.out.println("Indique a resolução que pretende\n" +
                                                    "1 - 640 x 480\n" +
                                                    "2 - 1280 x 720\n" +
                                                    "3 - 1920 x 1080\n" +
                                                    "4 - 3840 x 2160\n");
                                            try {
                                                opcaoCameraRes = sc.nextInt();
                                            } catch (Exception e) {
                                                System.out.println(
                                                        "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                                opcaoCameraRes = 0;
                                            }
                                        } while (opcaoCameraRes < 1 || opcaoCameraRes > 4);
                                        if (opcaoCameraRes == 1) {
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_x(640);
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_y(480);
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        } else if (opcaoCameraRes == 2) {
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_x(1280);
                                            System.out.println("Alteração realizada com sucesso!\n");

                                            ;
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_y(720);
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        } else if (opcaoCameraRes == 3) {
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_x(1920);
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_y(1080);
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        } else if (opcaoCameraRes == 4) {
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_x(3840);
                                            mainMethods.getCameraWithCode(codigoDispositivo).setResolution_y(2160);
                                            System.out.println("Alteração realizada com sucesso!\n");

                                        }

                                    } else if (opcaoCamera == 3) {
                                        System.out
                                                .println("Insira o tamanho que pretende que o ficheiro tenha em GB\n");
                                        double filesize = sc.nextDouble();
                                        mainMethods.getCameraWithCode(codigoDispositivo).setFilesize(filesize);
                                        System.out.println("Alteração realizada com sucesso!\n");

                                    } else {
                                        System.out.println(
                                                "Insira o novo consumo diário (em KWh (com vírgula em caso de número decimal.))\n");
                                        float consumo = sc.nextFloat();
                                        mainMethods.getCameraWithCode(codigoDispositivo)
                                                .setConsumoDiarioCamera(consumo); // consumo em KWh
                                        System.out.println("Alteração realizada com sucesso!\n");
                                        System.out.println("Alteração realizada com sucesso!\n");

                                    }

                                }
                            } /**/else if (opcaoDispositivos == 3) { // SPEAKER
                                System.out.println(mainMethods.getDivisao(divisao).values().toString());
                                int codigoDispositivo = 0;
                                do {
                                    System.out.println("Insira o código do dispositivo a alterar:\n");
                                    try {
                                        codigoDispositivo = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println(
                                                "Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
                                        opcaoDispositivos = 0;
                                    }
                                } while (!mainMethods.existeDispositivo(codigoDispositivo));
                                if (mainMethods.getDeviceWithCode(codigoDispositivo).isSpeaker()) {
                                    int opcaoSpeaker = 0;
                                    do {
                                        System.out.println("O que pretende alterar?\n" +
                                                "1 - Ligar/Desligar o speaker\n" +
                                                "2 - Alterar a rádio que está a tocar\n" +
                                                "3 - Alterar o volume atual\n" +
                                                "4 - Alterar o consumo diário\n");
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
                                            mainMethods.getDeviceWithCode(codigoDispositivo).setOFF();
                                            mainMethods.getSpeakerWithCode(codigoDispositivo).setconsumoDiarioSpeaker(0);
                                        } else {
                                            mainMethods.getDeviceWithCode(codigoDispositivo).setON();
                                            System.out.println("Alteração realizada com sucesso!\n");
                                        }
                                    } else if (opcaoSpeaker == 2) {
                                        System.out.println("Insira a rádio que deseja ouvir\n");
                                        String nomeRadio = sc.next();
                                        mainMethods.getSpeakerWithCode(codigoDispositivo)
                                                .setNomeRadio(nomeRadio);
                                        ;
                                    } else if (opcaoSpeaker == 3) {
                                        System.out.println("Insira o volume que deseja\n");
                                        int volume = sc.nextInt();
                                        mainMethods.getSpeakerWithCode(codigoDispositivo)
                                                .setVolume(volume); // consumo em KWh
                                        System.out.println("Alteração realizada com sucesso!\n");

                                    } else {
                                        System.out.println(
                                                "Insira o novo consumo diário (em KWh (com vírgula em caso de número decimal.))\n");
                                        float consumo = sc.nextFloat();
                                        mainMethods.getSpeakerWithCode(codigoDispositivo)
                                                .setconsumoDiarioSpeaker(consumo); // consumo em KWh
                                        System.out.println("Alteração realizada com sucesso!\n");

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
                        if (mainMethods.existeFornecedoremArticulador(codigoFornecedorToChangeCasa)) {
                            CasaInteligente ciTemp = mainMethods.getArt().getCasas().get(codigoCasaToChangeFornecedor);
                            int codigoAntigoFornecedor = mainMethods.getFornecedorCodeFromCasa(ciTemp);
                            mainMethods.getArt().getFornecedores().get(codigoAntigoFornecedor).getClientes()
                                    .remove(codigoCasaToChangeFornecedor);
                            mainMethods.getArt().getFornecedores().get(codigoFornecedorToChangeCasa).addCliente(ciTemp);
                        } else {
                            System.out.println("O fornecedor que mencionou não existe.\n");
                        }
                    } else {
                        System.out.println("A casa que mencionou não existe.\n");
                    }
                    break;
                case 8:
                    System.out.println("\nInsira a data para a qual pretende avançar\n");
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
                    int opcaoTime = 0;
                    do {
                        System.out.println("\nOs dispositivos das casas estiveram ligadas durante este período?\n"
                                + "1 - Sim\n" + "2 - Não\n");
                        try {
                            opcaoTime = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("\nInsira uma opção válida\n");
                            opcaoTime = 0;
                        }
                    } while (opcaoTime < 1 || opcaoTime > 2);
                    if (opcaoTime == 1) { // ver se a data introduzia é valida tipo perna
                        Map<Integer, Double> consumoDeCasa = new HashMap<Integer, Double>();
                        mainMethods.getArt().getCasas().values()
                                .forEach(x -> consumoDeCasa.put(x.getCode(), x.energiaTotalDiariaCasa()));
                        consumoDeCasa.values().forEach(x -> x *= dateDifference);
                        System.out.println("O consumo de energia por casa é:\n" + consumoDeCasa);
                        for (Fornecedor forneceperna : mainMethods.getArt().getFornecedores().values()) {
                            for (CasaInteligente casebre : forneceperna.getClientes().values()) {
                                mainMethods.placeFatura(dateToAdvance, forneceperna, casebre.getNifProprietario(),
                                        casebre.energiaTotalDiariaCasa(),
                                        forneceperna.precoTotalDiarioCliente(casebre.getCode()), casebre.getCode());
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, selecione uma das opções disponíveis.\n");
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
                        boolean existeFornecedor = mainMethods.existeFornecedoremArticulador(codigoFornecedorStat);
                        while (!existeFornecedor) {
                            System.out.println(
                                    "Fornecedor não existe!\n");
                            System.out.println(
                                    "Forneça um código válido de fornecedor que pretende consultar:\n");
                            codigoFornecedorStat = sc.nextInt();
                            existeFornecedor = mainMethods.existeFornecedoremArticulador(codigoFornecedorStat);
                        }
                        System.out.println(
                                "As facturas emitidas pelo fornecedor " + codigoFornecedorStat
                                        + " são:\n"
                                        + mainMethods.faturasByFornecedor(codigoFornecedorStat)
                                        + "\n");
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
                            System.out.println("Qual o código da casa que pretende consultar?\n");
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
                    } else if (opcaoForStat == 4) {
                        System.out.println(
                                "O rank de maior consumo de energia é:\n" + mainMethods.rankCasasComMaiorConsumo());
                    }
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
