import pandas as pd

def createBonusColumn(employees: pd.DataFrame) -> pd.DataFrame:
    #bonus would be 2x salary
    employees['bonus']=2*employees['salary']
    return employees
