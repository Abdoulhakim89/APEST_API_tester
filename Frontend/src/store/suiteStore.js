import { create } from "zustand";

const useSuiteStore = create((set)=>({
    suite:{
        suiteName:"",
        baseUrl:"",
        tests:[]
    },

    setSuiteName:(name) => set(state =>({suite:{...state.suite, suiteName:name}}) ),

    setBaseUrl: (url) => set(state => ({suite:{...state.suite, baseUrl:url}})),

    addTestCase:(testCase) => set(state => ({
        suite:{...state.suite, tests:[...state.suite.tests, testCase]}
    })),

    deleteTestCase:(index) => set(state => ({
        suite:{...state.suite, tests:state.suite.tests.filter((_,i)=> i !== index)}
    }))



}))

export default useSuiteStore;