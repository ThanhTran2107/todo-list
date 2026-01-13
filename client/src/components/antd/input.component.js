import { Input } from 'antd';
import { forwardRef } from 'react';

const TextField = forwardRef((props, ref) => <Input ref={ref} {...props} />);

// Forward all static properties from Input to TextField
Object.keys(Input).forEach(key => {
  TextField[key] = Input[key];
});

export { Input };
export { TextField };
