import requests


def getRequest():
    response = requests.get("https://datausa.io/api/data?drilldowns=Nation&measures=Population")
    response_in_json = response.json()
    source_name = response_in_json["source"][0]["annotations"]["source_name"]
    years = response_in_json["data"]
    start_year = response_in_json["data"][8]["Year"]
    end_year = response_in_json["data"][0]["Year"]
    # Calculation of peak population % and year
    max_population = max(years, key=lambda x: x['Population'])
    peak_year = max_population['Year']
    peak_population = max_population['Population']
    previous_population = next(item for item in years if item["Year"] == str(int(peak_year) - 1))['Population']
    max_growth_percentage = ((peak_population - previous_population) / previous_population) * 100
    # Calculation of the lowest population % and year
    min_growth_year = ""
    min_growth_percentage = float('inf')
    for i in range(1, len(years)):
        population = years[i]['Population']
        previous_population = years[i - 1]['Population']
        growth_percentage = ((population - previous_population) / previous_population) * 100

        if growth_percentage < min_growth_percentage:
            min_growth_percentage = growth_percentage
            min_growth_year = years[i]['Year']

    print(
        f"According to {source_name}, in {len(years)} years from {start_year} to {end_year}, peak population growth was {max_growth_percentage:.2f}% in {peak_year} and the lowest population increase was {min_growth_percentage:.2f}% in {min_growth_year}.")


getRequest()
