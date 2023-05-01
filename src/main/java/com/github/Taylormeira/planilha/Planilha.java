package com.github.Taylormeira.planilha;

import com.github.Taylormeira.exception.InventarioException;
import com.github.Taylormeira.models.Computador;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Planilha {
    public static final Path arquivo = Path.of("planilha.xlsx").toAbsolutePath();

    private HSSFWorkbook workbook;

    public List<Computador> carregar() throws InventarioException {

        if (Files.exists(arquivo)) {

            try (FileInputStream file = new FileInputStream(arquivo.toFile())) {
                workbook = new HSSFWorkbook(file);
            } catch (IOException e) {
                throw new InventarioException(e);
            }

        } else {
            workbook = new HSSFWorkbook();
        }

        HSSFSheet sheetComputador;

        if (workbook.getNumberOfSheets() == 0) {
            sheetComputador = workbook.createSheet("computador");
        } else {
            sheetComputador = workbook.getSheet("computador");
        }

        final List<Computador> computadores = new ArrayList<>();

        var iterable = sheetComputador.iterator();

        if (iterable.hasNext()) {
            iterable.next(); // Ignora o cabecalho
        } else {
            return computadores; // retorna a lista vazia
        }

        while (iterable.hasNext()) {
            Row cells = iterable.next();

            Computador computador = new Computador();

            Cell cellNome = cells.getCell(0);
            computador.setNome(cellNome.toString());

            Cell cellMemoria = cells.getCell(1);
            computador.setMemoria(cellMemoria.toString());

            Cell cellProcessador = cells.getCell(2);
            computador.setProcessador(cellProcessador.toString());

            Cell cellLocado = cells.getCell(3);
            computador.setLocado(cellLocado.getBooleanCellValue());

            computadores.add(computador);
        }

        return computadores;
    }


    public void salvar(List<Computador> computadores) {

        try {
            workbook.removeSheetAt(0);

            HSSFSheet sheetComputador = workbook.createSheet("computador");

            int index = 0;

            {
                HSSFRow cabecalho = sheetComputador.createRow(index);

                Cell cellNome = cabecalho.createCell(0);
                cellNome.setCellValue("Nome");
                Cell cellMemoria = cabecalho.createCell(1);
                cellMemoria.setCellValue("Memoria");
                Cell cellProcessador = cabecalho.createCell(2);
                cellProcessador.setCellValue("Processador");
                Cell cellLocado = cabecalho.createCell(3);
                cellLocado.setCellValue("locado");
            }

            for (Computador computador : computadores) {
                //Puxando informação da ultima row e criando uma nova
                HSSFRow row = sheetComputador.createRow(++index);

                //Fazendo GET dos valores que irão ser demonstrados ao usuário
//                computador.setNome("teste");    //// TODO: 01/05/2023 colocar a varivel que irá devolver a string do nome
//                computador.setProcessador("i9");//// TODO: 01/05/2023 colocar a varivel que irá devolver a string do Pocessador
//                computador.setMemoria("4Gb de Ram");///TODO: 01/05/2023 colocar a varivel que irá devolver a String da Memoria
//                computador.setLocado(false);    //// TODO: 01/05/2023 colocar a varivel que irá devolver a boolean da locação


                //Setando as informações nas celulas
                Cell cellNome = row.createCell(0);
                cellNome.setCellValue(computador.getNome());
                Cell cellMemoria = row.createCell(1);
                cellMemoria.setCellValue(computador.getMemoria());
                Cell cellProcessador = row.createCell(2);
                cellProcessador.setCellValue(computador.getProcessador());
                Cell cellLocado = row.createCell(3);
                cellLocado.setCellValue(computador.isLocado());
            }

            try (FileOutputStream outFile = new FileOutputStream(arquivo.toFile())) {
                workbook.write(outFile);
            }

            System.out.println("Arquivo Alterado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        } finally {
            System.out.println("Encerrada aplicação.");
        }
    }

}
