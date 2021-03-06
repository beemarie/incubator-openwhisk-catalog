// Licensed to the Apache Software Foundation (ASF) under one or more contributor
// license agreements; and to You under the Apache License, Version 2.0.

/**
 * Return word count as a binary number. This demonstrates the use of a blocking
 * invoke.
 */
var openwhisk = require('openwhisk');

function main(params) {
    var wsk = openwhisk({ignore_certs: params.ignore_certs || false});
    var str = params.payload;
    console.log("The payload is '" + str + "'");

    return wsk.actions.invoke({
        actionName: '/whisk.system/samples/wordCount',
        params: {
            payload: str
        },
        blocking: true
    }).then(activation => {
        console.log('activation:', activation);
        var wordsInDecimal = activation.response.result.count;
        var wordsInBinary = wordsInDecimal.toString(2) + ' (base 2)';
        return { binaryCount: wordsInBinary };
    });
}
