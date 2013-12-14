#!/usr/bin/Rscript

n = commandArgs(TRUE)[1]

if (is.na(n))
{
	stop("Usage: ./png.r 17")
}

filename = paste("tests/", n, ".png", sep = "")
png(filename)
colors = c("black", "darkblue", "cyan", "darkred", "red", "magenta", "green")
names = rep(NA, 7)
data = rep(NA, 7)
i = 1

for (pm in c(0.5, 0.9, "1.0"))
{
	pair = paste(pm, "_", 0.9, sep = "")
	names[i] = pair
	name = paste("tests/", n, "_", pair, ".ans", sep = "")
	data[i] = read.table(name)
	i = i + 1
}

for (pc in c(0.1, 0.5, 0.9, "1.0"))
{
	pair = paste(0.1, "_", pc, sep = "")
	names[i] = pair
	name = paste("tests/", n, "_", pair, ".ans", sep = "")
	data[i] = read.table(name)
	i = i + 1
}

optimum = scan(paste("tests/", n, ".out", sep = ""))
for (i in 1:7)
{
	data[[i]] = data[[i]] - optimum
}

xmin = .Machine$integer.max
xmax = 0
for (i in 1:7)
{
	xmin = min(xmin, min(data[[i]]))
	xmax = max(xmax, max(data[[i]]))	
}

plot(ecdf(data[[1]]), verticals = TRUE, do.points = FALSE, col = colors[1], main = n, xlab="najlepszy - optimum", ylab="Prawdopodobieństwo", xlim=c(xmin, xmax), ylim=c(0,1))
for (i in 2:7)
{
	lines(ecdf(data[[i]]), verticals = TRUE, do.points = FALSE, col = colors[i])
}

legend("topleft", names, col = colors, lty = 1);

print(filename)
