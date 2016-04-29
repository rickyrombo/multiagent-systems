df = read.csv("RatData.2016.Apr.26.00_56_50.csv")

numruns = 5
colors = rainbow(numruns)
run = df[df$run == 1, ]
plot(run$pop, type="l", col=colors[1])
for (i in 2:numruns) {
  run = df[df$run == i, ]
  # labels = c("Population", "Average Age", "Average Fight Count", "Average Mated", "Average Meals Eaten")
  
  lines(run$pop, type="l", col=colors[i])
  #lines(run$Average.Fight.Count, type="l", col=colors[3])
  #lines(run$Average.Mated, type="l", col=colors[4])
  #lines(run$Average.Meals.Eaten, type="l", col=colors[5])
  # legend("topleft", legend=labels, col=colors, pch=1)
  legend("topleft", legend=1:numruns, col=colors, pch=1)
}
run = df[df$run == 1, ]
plot(run$average.age, type="l", col=colors[1])
for (i in 2:numruns) {
  run = df[df$run == i, ]
  lines(run$average.age, type="l", col=colors[i])
  legend("topleft", legend=1:numruns, col=colors, pch=1)
}
run = df[df$run == 1, ]
plot(run$Average.Meals.Eaten., type="l", col=colors[1])
for (i in 2:numruns) {
  run = df[df$run == i, ]
  lines(run$Average.Meals.Eaten., type="l", col=colors[i])
  legend("topleft", legend=1:numruns, col=colors, pch=1)
}
run = df[df$run == 1, ]
plot(run$Average.Fight.Count., type="l", col=colors[1])
for (i in 2:numruns) {
  run = df[df$run == i, ]
  lines(run$Average.Fight.Count., type="l", col=colors[i])
  legend("topleft", legend=1:numruns, col=colors, pch=1)
}
run = df[df$run == 1, ]
plot(run$Average.Mated., type="l", col=colors[1])
for (i in 2:numruns) {
  run = df[df$run == i, ]
  lines(run$Average.Mated., type="l", col=colors[i])
  legend("topleft", legend=1:numruns, col=colors, pch=1)
}
