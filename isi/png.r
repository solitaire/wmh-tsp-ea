#!/usr/bin/Rscript

n = commandArgs(TRUE)[1]

if (is.na(n))
{
	stop("Usage: ./png.r 17")
}

filename = paste("tests/", n, ".png", sep = "")
png(filename)
colors = c("black", "darkred", "blue", "grey", "cyan", "red", "yellow", "green")
names = rep(NA, length(colors))
data = rep(NA, length(colors))
i = 1

for (pm in c(0.1, 0.5, 0.9, "1.0"))
{
	pair = paste(pm, "_0.0", sep = "")
	names[i] = pair
	name = paste("tests/", n, "_", pair, ".ans", sep = "")
	data[i] = read.table(name)
	i = i + 1
}

for (pc in c(0.1, 0.5, 0.9, "1.0"))
{
	pair = paste("0.0_", pc, sep = "")
	names[i] = pair
	name = paste("tests/", n, "_", pair, ".ans", sep = "")
	data[i] = read.table(name)
	i = i + 1
}

optimum = scan(paste("tests/", n, ".out", sep = ""))
for (i in 1:length(colors))
{
	data[[i]] = data[[i]] - optimum
}

xmin = .Machine$integer.max
xmax = 0
for (i in 1:length(colors))
{
	xmin = min(xmin, min(data[[i]]))
	xmax = max(xmax, max(data[[i]]))	
}

plot(ecdf(data[[1]]), verticals = TRUE, do.points = FALSE, col = colors[1], main = n, xlab="najlepszy - optimum", ylab="Prawdopodobieństwo", xlim=c(xmin, xmax), ylim=c(0,1))
for (i in 2:length(colors))
{
	lines(ecdf(data[[i]]), verticals = TRUE, do.points = FALSE, col = colors[i])
}

legend("topleft", names, col = colors, lty = 1);

print(filename)
