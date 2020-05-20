package pt.ua.nextweather.content;

public class WeatherInfo {

    private String forecastDate[] = new String[5];
    private String precipitaProb[]= new String[5];
    private String tMin[]= new String[5];
    private String tMax[]= new String[5];
    private String predWindDir[]= new String[5];
    private String idWeatherType[]= new String[5];
    private String classWindSpeed[]= new String[5];

    public WeatherInfo(String details){
        for(int i = 0; i < 5; i++) {
            forecastDate[i] = details.split("Weather{")[i+1].split("forecastDate='")[1].split("',")[0];
            precipitaProb[i] = details.split("Weather{")[i+1].split("precipitaProb=")[1].split(",")[0];
            tMin[i] = details.split("Weather{")[i+1].split("tMin=")[1].split(",")[0];
            tMax[i] = details.split("Weather{")[i+1].split("tMax=")[1].split(",")[0];
            predWindDir[i] = details.split("Weather{")[i+1].split("predWindDir='")[1].split("',")[0];
            idWeatherType[i] = details.split("Weather{")[i+1].split("idWeatherType=")[1].split(",")[0];
            classWindSpeed[i] = details.split("Weather{")[i+1].split("classWindSpeed=")[1].split(",")[0];
        }
    }
}
