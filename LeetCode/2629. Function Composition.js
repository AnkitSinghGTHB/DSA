var compose = function(functions) {
    //so just recursion?
    return function(x) {//like if f,g,h are fns, then first h, then g(h), then f(g(h))
        for (let i = functions.length - 1; i >= 0; i--) {
            x = functions[i](x);
        }
        return x;
    }
};
