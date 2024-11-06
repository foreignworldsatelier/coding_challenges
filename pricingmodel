import pandas as pd
import numpy as np
import math as ma
import matplotlib.pyplot as plt
plt.close("all")
import statistics as stat
from pandas import DataFrame
import sqlalchemy
from sqlalchemy import create_engine
pass
#assumptions Use this section to set initial parameters for the model
#Current Salary per employee
eSalary = 65000
#Max Salary on the market
mESalary = 72000
#weight to assign employee labor in quality score
eQWeight = .45
#Number of Managers for the project, based on number of employees
# nManagers = round(nEmployees/5)
#Current manager salary
mSalary = 70000
#weight to assign manager labor in quality score
mQWeight = .25
#Max salary for comparable position on the market
mMSalary = 95000
#Number of directors, based on managers, but should be based on total company workforce, not project
# nDirectors = round(nManagers/3)
#Current Salary for the director
dSalary = 120000
#weight to assign director labor in quality score
dQWeight = .3
#Max salary for comparable position on the market
mDSalary = 180000
#Use this in case building a physical product
# materialCost = 10000
#Hours is would take 1 person to complete the design phase
designHours = 320
#Number of employees to assign to the design phase
assignedDE = 3
#Optimal number to assign from a work hours perspective
maxDesignE = 4
#Hours it would take 1 employee to complete the build phase
buildHours = 480
#Number of employees assigned to the build phase
assignedBE = 6
#Optimal number of employees to assign from a work hours perspective
maxBuildE = 8
#Hours it would take 1 employee to complete QA
qaHours = 210
#Number of employees assigned to QA phase
assignedQE = 2
#Optimal number of employees to do QA from a work hours perspective
maxQaE = 3
#Cost to keep the lights on per year and other non-salary costs
overHead = 500000
#Target growth rate
overHeadGrowth = .3
#Estimate quality of work as a function of paid salary vs market max, 
#assuming a higher salary = higher quality talent. 
#Of course other factors can be taken into account such as product features (capacity,performance,flexibility etc) 
#but this simplified version will be used in the demo
eQScore = (mESalary-(mESalary-eSalary))/mESalary
mQScore = (mMSalary-(mMSalary-mSalary))/mMSalary
dQScore = (mDSalary-(mDSalary-dSalary))/mDSalary
qScore = (((eQScore * eQWeight) + (mQScore * mQWeight) + (dQScore * dQWeight))/(eQWeight+mQWeight+dQWeight)) *10
print(eQScore)
print(mQScore)
print(dQScore)
print(qScore)
#Calculate the hourly rate for each level as well as overhead cost 
eHourly = (eSalary)/(8*5*52)
mHourly = mSalary/(8*5*52)
dHourly = dSalary/(8*5*52)
oHourly = overHead/(8*5*52)
#Use workHours function to calculate hourly reduction for each phase based on employees assigned
designHours_Adj = workHours(maxDesignE,designHours,assignedDE)
buildHours_Adj = workHours(maxBuildE,buildHours,assignedBE)
qaHours_Adj = workHours(maxQaE,qaHours,assignedQE)
totalHours = designHours_Adj+buildHours_Adj+qaHours_Adj
#Find cost of employees
designECost = designHours_Adj * assignedDE * eHourly
buildECost = buildHours_Adj * assignedBE * eHourly
qaECost = qaHours_Adj * assignedQE * eHourly
employeeCost = designECost + buildECost + qaECost
print(designECost)
print(buildECost)
print(qaECost)
print(employeeCost)
#Find cost of managers
designManagers = ma.ceil(assignedDE/5)
buildManagers = ma.ceil(assignedBE/5)
qaManagers = ma.ceil(assignedQE/5)
designMCost = designManagers * designHours_Adj * mHourly
buildMCost  = buildManagers * buildHours_Adj * mHourly
qaMCost = qaManagers * qaHours_Adj * mHourly
managerCost = designMCost + buildMCost + qaMCost
print(designMCost)
print(buildMCost)
print(qaMCost)
print(managerCost)
#Find cost of directors
nDirectors = ma.ceil((designManagers + buildManagers +qaManagers)/3)
directorHours = totalHours
directorCost = nDirectors * directorHours * dHourly
print(nDirectors)
print(directorHours)
print(dHourly)
print(directorCost)
totalHrCost = employeeCost + managerCost + directorCost
print(totalHrCost)
overHeadCost = oHourly * totalHours
print(overHeadCost)
totalCost = totalHrCost # + materialCost #Include if building physical products
print(totalCost)
#Establish competition pricing,quality, market share. 
#Note market share % is less than 100 as only top three competitors are considered, not the long tail
d = {'competitor':['c1','c2','c3'],'price':[90000,200000,175000],'quality':[5,9,7],'market_share':[.12,.3,.08]}
compDf = pd.DataFrame(data=d)
print(compDf)
for index, row in compDf.iterrows():
    currentQ = compDf.iloc[index]['quality']
    if qScore >= currentQ:
        pricePoint = compDf.iloc[index]['price']
        print("Competitor: ", compDf.iloc[index]['competitor'],"Price Point: ",pricePoint,"Our qScore: ",  
         qScore,"Competitor qScore: ",currentQ)
    else:
        print("No Match")
#Compare competitor pricing to quality to see how strong the correlation is. 
    
qScore1 = compDf.iloc[0]['quality']
qScore2 = compDf.iloc[1]['quality']
qScore3 = compDf.iloc[2]['quality']
    
pricePoint1 = compDf.iloc[0]['price']
pricePoint2 = compDf.iloc[1]['price']
pricePoint3 = compDf.iloc[2]['price']
    
pScore1 = (pricePoint1/qScore1)
pScore2 = (pricePoint2/qScore2)
pScore3 = (pricePoint3/qScore3)
    
pScores = [pScore1,pScore2,pScore3]
    
avgPScore = np.mean(pScores)
stdPScore = stat.stdev(pScores)
coVPScore = stdPScore/avgPScore
print(avgPScore)
print(stdPScore)
print(coVPScore)
    
#The Covariance is low, so for the next step we will 
#use the average of price/quality as a multiplier on our quality score to try
#to right size our pricing to the market
#For other cases a better indicator may be used, for example some sectors could see
#consistent quality with marketing driving sales
#Take competitive price point vs overhead to run the business and 
#determine how many projects are needed per year to breakeven
#Manually set this to override calculations
cPricePoint = avgPScore * qScore
revenueLessS = cPricePoint - totalCost
print("Competetive Price Point: ",cPricePoint)
print("Project Revenue Less Salaries: ",revenueLessS)
breakEvenProjectCt = ma.ceil(overHead/revenueLessS)
totalRevenue = cPricePoint*breakEvenProjectCt
totalRevenueLessS = revenueLessS *breakEvenProjectCt
profit = totalRevenueLessS-overHead
print("Projects needed to break even: ",breakEvenProjectCt)
print("Total revenue:", totalRevenue)
print("Total revenue less salaries: ",totalRevenueLessS)
print("Overhead: ",overHead)
print("Profit: ",profit)
p = {'year':[2021],'revenue': [totalRevenue],'revenue_less_salaries':[totalRevenueLessS],
     'overhead':[overHead],'profit':[profit],'break_even_project_count':[breakEvenProjectCt]}
growthAnalysis = pd.DataFrame(data=p)
print(growthAnalysis)
Forecast = 5
year=2021
totalRevenue1 = totalRevenue
totalRevenueLessS1 = totalRevenueLessS
overHead1 = overHead
profit1 = profit
revenueLessS1 = revenueLessS
cPricePoint1 = cPricePoint
totalCost1 = totalCost
for x in range(Forecast):
    year += 1
    print("year = ",year)
    cPricePoint1 = cPricePoint1 *1.02
    print("Price Point = ",cPricePoint1)
    totalCost1 = totalCost1 * 1.02
    print("Total Cost =", totalCost1)
    revenueLessS1 = cPricePoint1 - totalCost1
    
    print("Revenue Less Salaries = ",revenueLessS1)
    overHead1 = overHead1 * (1+overHeadGrowth)
    print("over head = ", overHead1)
    
    breakEvenProjectCt1 = ma.ceil(overHead1/revenueLessS1)
    print("break even projects =",breakEvenProjectCt1)
    totalRevenue1 = cPricePoint*breakEvenProjectCt
    
    print("price point = ",totalRevenue1)
    
    totalRevenueLessS1 = revenueLessS1 *breakEvenProjectCt1
    print("total cost = ",totalRevenueLessS1)
    
    profit1 = totalRevenueLessS1 - overHead1
    print("profit = ",profit1)
    
    
    p2 = {'year':[year],'revenue': [totalRevenue1],'revenue_less_salaries':[totalRevenueLessS1],
     'overhead':[overHead1],'profit':[profit1],'break_even_project_count':[breakEvenProjectCt1]}
    growthAnalysis2 = pd.DataFrame(data=p2)
    growthAnalysis = growthAnalysis.append(growthAnalysis2)
    
    growthAnalysis.plot(x="year",secondary_y="break_even_project_count")
# growthAnalysis["year","break_even_project_count"].plot(x="year")
