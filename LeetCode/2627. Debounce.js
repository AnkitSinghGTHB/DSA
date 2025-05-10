var debounce = function(fn, t) {
    //uh what? oh ok nvm
    let time;
    return function(...args) {
        clearTimeout(time);
        time = setTimeout(()=>{
            fn.apply(this,args);//recursion
        },t)
    }
};
