package com.ddr.drugtrace.util;

import cn.hutool.core.io.file.FileReader;
import org.web3j.codegen.SolidityFunctionWrapperGenerator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;


public class SolidityUtils {
    //文件基础路径
    private static String basePath = "D:\\sourcecode\\drug-trace\\src\\main\\resources\\solidity";

    // 私有实例，类初始化就加载
    private static final SolidityUtils solidityGenerateUtil = new SolidityUtils();

    // 私有构造方法
    private SolidityUtils() {}

    // 公共的、静态的获取实例方法
    public static SolidityUtils getInstance() {
        return solidityGenerateUtil;
    }

    /**
     * 获取abi josn字符串
     * @return
     */
    public static String getAbiJson(){
        String abiPath = basePath + "utils/abi.json";
        FileReader abiReader = new FileReader(abiPath);
        String abiResult = abiReader.readString();
        return abiResult;
    }
    /**
     * 获取bytecode json字符串
     * @return
     */
    public static String getBytecodeJson(){
        String bytecodePath = basePath + "utils/bytecode.json";
        FileReader bytecodePathReader = new FileReader(bytecodePath);
        String bytecodeResult = bytecodePathReader.readString();
        return bytecodeResult;
    }

    public static void genAbiAndBin(){

        String abiJson = SolidityUtils.getInstance().getAbiJson();
        String bytecodeJson = SolidityUtils.getInstance().getBytecodeJson();

        String abiFileName = "SolidityERC721.abi";
        String binFileName = "SolidityERC721.bin";
        generateAbiAndBin(abiJson,bytecodeJson,abiFileName,binFileName);
    }

    public static void generateAbiAndBin(String abi,String bin,String abiFileName,String binFileName){
        File abiFile = new File(basePath + "contract/"+abiFileName);
        File binFile = new File(basePath + "contract/"+binFileName);
        BufferedOutputStream abiBos = null;
        BufferedOutputStream binBos = null;
        try{
            FileOutputStream abiFos = new FileOutputStream(abiFile);
            FileOutputStream binFos = new FileOutputStream(binFile);
            abiBos = new BufferedOutputStream(abiFos);
            binBos = new BufferedOutputStream(binFos);
            abiBos.write(abi.getBytes());
            abiBos.flush();
            binBos.write(bin.getBytes());
            binBos.flush();
            generateJavaFile(abiFile.getPath(),binFile.getPath());
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            if(abiBos != null){
                try{
                    abiBos.close();;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(binBos != null){
                try {
                    binBos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void generateJavaFile(String abiFile, String binFile){
        String generateFile = basePath + "/contract/";
        generateClass(abiFile,binFile,generateFile,"");
    }

    public static void generateClass(String abiFile,String binFile,String generateFile,String className){
        String[] args = Arrays.asList(
                "-a",abiFile,
                "-b",binFile,
                "-p","",
                "-o",generateFile,
                "-c",className
        ).toArray(new String[0]);
        Stream.of(args).forEach(System.out::println);
        SolidityFunctionWrapperGenerator.main(args);
    }

    public static void main(String[] args) {
        generateClass(basePath+"/__DrugTrace_sol_DrugTrace.abi",basePath+"/__DrugTrace_sol_DrugTrace.bin",basePath+"/contract/","DrugTrace");
    }

}