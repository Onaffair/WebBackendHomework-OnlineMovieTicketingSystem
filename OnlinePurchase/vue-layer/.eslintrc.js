module.exports = {
    root: true,
    env: {
        node: true,
    },
    extends: [
        'plugin:vue/vue3-essential', // 如果是 Vue 2 项目，请改为 'plugin:vue/essential'
        'eslint:recommended',
    ],
    parserOptions: {
        ecmaVersion: 2020, // 确保支持 ES2020 语法
        sourceType: 'module', // 启用模块语法
        parser: 'babel-eslint', // 使用 Babel 解析器
    },
    rules: {
        'vue/multi-word-component-names': 'off', // 可选：禁用多词组件名规则
        // 其他规则
    },
};
