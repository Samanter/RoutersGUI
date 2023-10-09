package UI.Misc;

import System.Functions;

public class LoadFunctionsData {
    private Functions functions;
    private String file_path;
    
    public LoadFunctionsData(Functions functions, String file_path) {
        this.functions = functions;
        this.file_path = file_path;
    }
    
    public Functions getFunctions() {
        return functions;
    }

    public String getFilePath() {
        return file_path;
    }
}
