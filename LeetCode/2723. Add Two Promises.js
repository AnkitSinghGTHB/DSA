var addTwoPromises = async function(promise1, promise2) {
    //we dont need to care about the time, just the resovle
    const [v1,v2] = await Promise.all([promise1,promise2]);
    return v1+v2;
};
