function setSessionProp(id, value) {
    if (typeof value === 'object')
        value = JSON.stringify(value);
    sessionStorage.setItem(id, value);
}

function getSessionProp(id) {
    const value = sessionStorage.getItem(id);
    try {
        return JSON.parse(value);
    } catch (e) {
        return value;
    }
}