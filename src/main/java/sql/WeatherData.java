package sql;

public class WeatherData {
    public String   city;
    public Double   current_temp;
    public Integer  sunrise;
    public Integer  sunset;
    public String   weather_description;
    public String   weather_icon;
    public Double   min_temp;
    public Double   max_temp;
    public Double   wind_speed;
    public Integer  wind_direction;
    public String   updated_date;

    public Integer  day_1;
    public String   icon_1;
    public String   type_1;
    public Double   min_temp_1;
    public Double   max_temp_1;

    public Integer  day_2;
    public String   icon_2;
    public String   type_2;
    public Double   min_temp_2;
    public Double   max_temp_2;

    public Integer  day_3;
    public String   icon_3;
    public String   type_3;
    public Double   min_temp_3;
    public Double   max_temp_3;

    public Integer  day_4;
    public String   icon_4;
    public String   type_4;
    public Double   min_temp_4;
    public Double   max_temp_4;

    public WeatherData(String   city,
                       Double   current_temp,
                       Integer  sunrise,
                       Integer  sunset,
                       String   weather_description,
                       String   weather_icon,
                       Double   min_temp,
                       Double   max_temp,
                       Double   wind_speed,
                       Integer  wind_direction,
                       String   updated_date,

                       Integer  day_1,
                       String   icon_1,
                       String   type_1,
                       Double   min_temp_1,
                       Double   max_temp_1,

                       Integer  day_2,
                       String   icon_2,
                       String   type_2,
                       Double   min_temp_2,
                       Double   max_temp_2,

                       Integer  day_3,
                       String   icon_3,
                       String   type_3,
                       Double   min_temp_3,
                       Double   max_temp_3,

                       Integer  day_4,
                       String   icon_4,
                       String   type_4,
                       Double   min_temp_4,
                       Double   max_temp_4) {
        this.city = city;
        this.current_temp = current_temp;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.weather_description = weather_description;
        this.weather_icon = weather_icon;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.updated_date = updated_date;

        this.day_1 = day_1;
        this.icon_1 = icon_1;
        this.type_1 = type_1;
        this.min_temp_1 = min_temp_1;
        this.max_temp_1 = max_temp_1;

        this.day_2 = day_2;
        this.icon_2 = icon_2;
        this.type_2 = type_2;
        this.min_temp_2 = min_temp_2;
        this.max_temp_2 = max_temp_2;

        this.day_3 = day_3;
        this.icon_3 = icon_3;
        this.type_3 = type_3;
        this.min_temp_3 = min_temp_3;
        this.max_temp_3 = max_temp_3;

        this.day_4 = day_4;
        this.icon_4 = icon_4;
        this.type_4 = type_4;
        this.min_temp_4 = min_temp_4;
        this.max_temp_4 = max_temp_4;
    }
}
